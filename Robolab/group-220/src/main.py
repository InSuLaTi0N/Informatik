#!/usr/bin/env python3

import ev3dev.ev3 as ev3
import logging
import os
import paho.mqtt.client as mqtt
import uuid

# temp
from motor import Motor
from sensor import ColorSensor
from linefollower import LineFollower
from directiondetermination import DirectionDetermination

from communication import Communication
from odometry import Odometry
from planet import Direction, Planet
# from calibration import Calibration
from decision import Decision

from typing import Tuple, List, Set

client = None  # DO NOT EDIT


def run():
    # DO NOT CHANGE THESE VARIABLES
    #
    # The deploy-script uses the variable "client" to stop the mqtt-client after your program stops or crashes.
    # Your script isn't able to close the client after crashing.
    global client

    client_id = '220' + str(uuid.uuid4())  # Replace YOURGROUPID with your group ID
    client = mqtt.Client(client_id=client_id,  # Unique Client-ID to recognize our program
                         clean_session=True,  # We want a clean session after disconnect or abort/crash
                         protocol=mqtt.MQTTv311  # Define MQTT protocol version
                         )
    log_file = os.path.realpath(__file__) + '/../../logs/project.log'
    logging.basicConfig(filename=log_file,  # Define log file
                        level=logging.DEBUG,  # Define default mode
                        format='%(asctime)s: %(message)s'  # Define default logging format
                        )
    logger = logging.getLogger('RoboLab')

    # THE EXECUTION OF ALL CODE SHALL BE STARTED FROM WITHIN THIS FUNCTION.
    # ADD YOUR OWN IMPLEMENTATION HEREAFTER.

    print("Hello World!")
    # offset = round(Calibration().calibrate(), 2)
    # line_follow = LineFollower()
    # motor = Motor()
    # determination = DirectionDetermination()

    # while not determination.is_point():
    #     line_follow.line_follow()

    # motor.stop()

    """variable declaration"""
    motor = Motor()
    color_sensor = ColorSensor()
    odometry = Odometry()
    communication = Communication(client, logger, "explorer/220")
    planet = Planet()
    line_follow = LineFollower()
    determination = DirectionDetermination()
    decision = Decision()
    current_node: Tuple[Tuple[int, int], Direction]
    open_directions: List[Tuple[Tuple[int, int], Direction]] = [] #speichert alle knoten mit allen offenen pafen
    past_paths: Set[Tuple[Tuple[int, int], Direction]] = set()     #vgl zu open_directions
    position_list: List[Tuple[int, int]] = []                      #liste für motorpositionen (odometry)
    x: int                                                         #speichert direction-integer
    path = None                                                    #speichert shortest path
    done = False
    dir_to_int = {
        Direction.NORTH: 0,
        Direction.EAST: 90,
        Direction.SOUTH: 180,
        Direction.WEST: 270
    }
    int_to_dir = {
        0: Direction.NORTH,
        90: Direction.EAST,
        180: Direction.SOUTH,
        270: Direction.WEST
    }
    """#depth_first_search"""
    while not determination.is_point():
        line_follow.line_follow()  # fahre bis zur 1. node

    determination.point_center()    # richte nach node-mitte aus
    ev3.Sound.beep()
    communication.ready()
    while not communication.planet_is_done: #warte auf server
        pass
    current_node = ((communication.path.get("payload").get("startX"),
                     communication.path.get("payload").get("startY")),
                    int_to_dir[communication.path.get("payload").get("startDirection")])
                    #weise node zu (auf der er steht)
    open_directions.append(current_node)
                    #füge current_node mit start-diretion zur sammlung hinzu

    while not communication.done:                       #wiederhole alles bis explo.complete nachricht kam
        position_list.append(current_node[0])
        n = determination.scan(current_node[0], current_node[1])
        for key, value in n.items():
            for v in value:
                if (key, v) not in past_paths:
                    open_directions.append((key, v))
        current_end_direction = int_to_dir[(communication.path["payload"]["endDirection"] - 180) % 360]
        #print(current_end_direction)

        """target-wahl"""
        if communication.target["payload"]["targetX"] != None:  #falls die node eine target-nachricht geschickt hat
                                        #fahre direkt zum target
            path = planet.shortest_path(current_node[0], (communication.target["payload"]["targetX"],
                                                          communication.target["payload"]["targetY"]))
            if path is not None:
                print("Shortest path direction: " + str(dir_to_int[path[0]]))
                x = dir_to_int[path[0]]
            else:
                print("Normal path selection: " + str(decision.select_direction(current_end_direction, current_node, open_directions, past_paths, planet)))
                x = decision.select_direction(current_end_direction, current_node, open_directions, past_paths, planet)
        else:
            x = decision.select_direction(current_end_direction, current_node, open_directions, past_paths, planet)

        communication.path_select(x)        #teile dem server die ausgewählte richtung mit

        def _turn(current, start):          # dreht den brick, je nach ausgewählter richtung
            if current == start:
                pass
            elif (current + 90) % 360 == start:
                motor.turn_right(45)
                while not color_sensor.is_black():
                    motor.run_direct(25, -25)
            elif (current - 90) % 360 == start:
                motor.turn_left(45)
                while not color_sensor.is_black():
                    motor.run_direct(-25, 25)
            elif (current - 180) % 360 == start:
                motor.turn_left(135)
                while not color_sensor.is_black():
                    motor.run_direct(-25, 25)
            motor.stop()

        _turn((communication.path["payload"]["endDirection"] - 180) % 360,
              communication.pathSelect["payload"]["startDirection"])
        motor.reset()

        #print(f"currentMotorPosition {motor.get_position()}")
        # motor.reset()
        #print(f"currentMotorPosition {motor.get_position()}")
        while not determination.is_point():         # fahre zur nächste node, checke ob path is blocked
            if line_follow.blocked():
                print("Blocked!")
                communication.path["payload"]["pathStatus"] = "blocked"
            #print(f"listPosition: {motor.get_position()}")
            line_follow.line_follow()
            position_list.append(motor.get_position())
        motor.stop()

        #print(f"current_payload: {communication.path}")
        if communication.path["payload"]["pathStatus"] == "free":
            odo_data = odometry.calculate((communication.pathSelect["payload"]["startX"],
                                           communication.pathSelect["payload"]["startY"]),
                                          communication.pathSelect["payload"]["startDirection"], position_list)
        else:
            odo_data = {
                "currentPosition": [communication.path["payload"]["endX"], communication.path["payload"]["endY"]],
                "currentDirection": (communication.pathSelect["payload"]["startDirection"] - 180) % 360
            }
        #odometrieberechnung je nach dem ob blocked oder nicht

        determination.point_center()
        ev3.Sound.beep()
        #print(f"odometry: {odo_data}")
        communication.new_path(odo_data["currentPosition"][0],
                               odo_data["currentPosition"][1],
                               (dir_to_int[odo_data["currentDirection"]] - 180) % 360,
                               communication.path["payload"]["pathStatus"])
            #teile dem server mit, dass ein neuer pfad aufgedeckt wurde
        while not communication.path_is_done:   # warte bis server antwortet
            pass
        position_list.clear()
        # print(current_node)
        # print(odo_data["currentPosition"])

        if path is None and communication.path["payload"]["pathStatus"] == "free":
            #abfrage damit pfad auf planet eingetragen wirde
            planet.add_path(((communication.path["payload"]["startX"], communication.path["payload"]["startY"]),
                             int_to_dir[communication.path["payload"]["startDirection"]]),
                            ((communication.path["payload"]["endX"], communication.path["payload"]["endY"]),
                             int_to_dir[communication.path["payload"]["endDirection"]]),
                            communication.path["payload"]["pathWeight"])
            for i in communication.pathUnveiled: #falls pfade aufgedeckt wurden, füge alle hinzu
                if i["payload"]["pathStatus"] == "free":
                    planet.add_path(((i["payload"]["startX"], i["payload"]["startY"]),
                                    int_to_dir[i["payload"]["startDirection"]]),
                                    ((i["payload"]["endX"], i["payload"]["endY"]),
                                    int_to_dir[i["payload"]["endDirection"]]),
                                    i["payload"]["pathWeight"])
                    planet.add_path(((i["payload"]["endX"], i["payload"]["endY"]),
                                    int_to_dir[i["payload"]["endDirection"]]),
                                    ((i["payload"]["startX"], i["payload"]["startY"]),
                                    int_to_dir[i["payload"]["startDirection"]]),
                                    i["payload"]["pathWeight"])
                    if ((i["payload"]["startX"], i["payload"]["startY"]), int_to_dir[communication.path["payload"]["startDirection"]]) not in open_directions:
                        open_directions.append(((i["payload"]["startX"],
                                                i["payload"]["startY"]),
                                                int_to_dir[i["payload"]["startDirection"]]))
                    past_paths.add(((i["payload"]["startX"],
                                     i["payload"]["startY"]),
                                    int_to_dir[i["payload"]["startDirection"]]))
                    past_paths.add(((i["payload"]["endX"],
                                     i["payload"]["endY"]),
                                    int_to_dir[i["payload"]["endDirection"]]))
                else:
                    past_paths.add(((i["payload"]["startX"],
                                    i["payload"]["startY"]),
                                    int_to_dir[i["payload"]["startDirection"]]))
            past_paths.add(((communication.path["payload"]["startX"], communication.path["payload"]["startY"]),
                            int_to_dir[communication.path["payload"]["startDirection"]]))
            past_paths.add(((communication.path["payload"]["endX"], communication.path["payload"]["endY"]),
                            int_to_dir[communication.path["payload"]["endDirection"]]))
            #füge aufgedeckte pfade zu past_paths hinzu da diese nichtmehr abgefahren werden müssen

        # open_directions.remove((current_node[0], current_end_direction))
        communication.path["payload"]["pathStatus"] = "free" #reset pathstatus

        current_node = ((communication.path["payload"]["endX"],
                         communication.path["payload"]["endY"]),
                        int_to_dir[(communication.path["payload"]["endDirection"] - 180) % 360])
            #current_node (node des bricks) auf "neue node" gesetzt -> ziel des bricks
        print(past_paths)
        done = True
        for o in open_directions: #wenn es keine offenen pfade mehr gibt -> sende nachricht, breche loop ab
            if o in past_paths:
                done = False
        if done:
            communication.exploration_completed()
        if communication.path["payload"]["endX"] == communication.target["payload"]["targetX"] and communication.path["payload"]["endY"] == communication.target["payload"]["targetY"]:
            #sende nachricht falls target-befehl vom server erfüllt wurde
            communication.target_reached()


# DO NOT EDIT
if __name__ == '__main__':
    run()
