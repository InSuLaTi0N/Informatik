# !/usr/bin/env python3
import math
from typing import List, Tuple
from planet import Direction
import time

class Odometry:
    def __init__(self):
        """
        Initializes odometry module
        """

        # YOUR CODE FOLLOWS (remove pass, please!)
        self.diameter = 60 # mm
        self.wheelbase = 120 # mm
    
    """
    1. need to get positions of each timing
    2. get the diffrence of near position
    3. switch it to radian
    4. get alpha and beta
    5. get s, delta_x and delta_y
    6. get new direction
    7. sum delta_x and delta_y
    """

    def calculate(self, start_position: Tuple[int, int], start_direction: Direction, position_list: List[Tuple[int, int]]):
        
        difference_list = []
        if(len(position_list) > 10):
            del position_list[0:10] # lassen grosse Abweichung weg
        for i in range(1, len(position_list)):
            front = position_list[i - 1]
            behind = position_list[i]
            difference_list.append((behind[0] - front[0], behind[1] - front[1]))
            
            
        # x = start_position[0]
        # y = start_position[1]
        x = 0
        y = 0
        direction = math.radians(start_direction)
        print(f"origin direction: {direction}")
        for i in range(0, len(difference_list)):
            l = difference_list[i][0]
            r = difference_list[i][1]
            dl = l * self.diameter * math.pi / 360
            dr = r * self.diameter * math.pi / 360
            alpha = (dr - dl) / self.wheelbase
            beta = alpha / 2
            if(alpha != 0):
                s = ((dr + dl) / alpha) * math.sin(beta)
            else:
                s = dr
            delta_x = math.sin(direction + beta) * s
            delta_y = math.cos(direction + beta) * s
            direction -= alpha
            x += delta_x
            y += delta_y
        
        print(f"calculated direction: {direction}")
        direction = math.degrees(direction) % 360
        print(f"end direction: {direction}")
        if(direction < 45 or direction > 315):
            temp_direction = Direction.NORTH
        elif(direction >= 45 and direction < 135):
            temp_direction = Direction.EAST
        elif(direction >= 135 and direction < 225):
            temp_direction = Direction.SOUTH
        elif(direction >= 225 and direction <= 315):
            temp_direction = Direction.WEST
        else:
            return {"direction invalid": direction}
        print(f"changedX: {round(x / 500)}, changedY: {round(y / 500)}")
        print(f"start position: {start_position}")
        x = start_position[0] + round(x / 500)
        y = start_position[1] + round(y / 500)
            
        return {"currentPosition": (x, y), "currentDirection": temp_direction}