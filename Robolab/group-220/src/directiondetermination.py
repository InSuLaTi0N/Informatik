from motor import Motor
from sensor import ColorSensor
from planet import Direction
from typing import List, Tuple, Dict

class DirectionDetermination:
    
    def __init__(self):
        self.color_sensor = ColorSensor()
        self.motor = Motor()
        self.direction_map = (Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
    
    def is_point(self): # check communication node
        return self.color_sensor.is_red() or self.color_sensor.is_blue()
    
    def point_center(self):
        if(self.is_point()):
            self.motor.run_abs_dis(8)
        
    
    def has_line(self):
        found = False
        self.motor.scan_turn_left(90)
        while(self.motor.is_running()):
            if(self.color_sensor.is_black()):
                found = True
        self.motor.stop()
        return found
    
    def scan(self, position, direction):
        direction_list = {}
        self.motor.turn_right(135)
        
        # right scanning
        direction = (direction + 90) % 360
        print(f"scanning {self.direction_map[int(direction / 90)]}")
        self._sub_scan(position, direction, direction_list)
        
        # middle scanning
        direction = (direction - 90) % 360
        print(f"scanning {self.direction_map[int(direction / 90)]}")
        self._sub_scan(position, direction, direction_list)
        
        # left scanning
        direction = (direction - 90) % 360
        print(f"scanning {self.direction_map[int(direction / 90)]}")
        self._sub_scan(position, direction, direction_list)
        
        self.motor.turn_right(135)
        
        return direction_list
        
    def _sub_scan(self, position, direction, list: Dict[Tuple[int, int], List[Direction]]):
        if(self.has_line()):
            if(position in list.keys()):
                list[position].append(self.direction_map[int(direction / 90)])
            else:
                list[position] = [self.direction_map[int(direction / 90)]]
                