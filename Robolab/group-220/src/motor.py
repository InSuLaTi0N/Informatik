import ev3dev.ev3 as ev3
# import time
import math

class Motor:
    
    def __init__(self):
        self.left_motor = ev3.LargeMotor("outA")
        self.right_motor = ev3.LargeMotor("outD")
        # self.medium_motor = ev3.MediumMotor("outB")
        self.left_motor.reset()
        self.right_motor.reset()
        # self.medium_motor.reset()
        
        self.wheelbase = 12
        self.diameter = 6
    
    
    def turn_left(self, angle): # in degree
        self.reset()
        ticks = (angle * self.wheelbase) / self.diameter
        self.left_motor.position_sp = -ticks
        self.right_motor.position_sp = ticks
        self.left_motor.speed_sp = 70
        self.right_motor.speed_sp = 70
        self.left_motor.command = "run-to-abs-pos"
        self.right_motor.command = "run-to-abs-pos"
        self.left_motor.wait_until_not_moving()
        self.right_motor.wait_until_not_moving()
        self.reset()
            
        
    def turn_right(self, angle):
        self.turn_left(-angle)
        
    def scan_turn_left(self, angle):
        self.reset()
        ticks = (angle * self.wheelbase) / self.diameter
        self.left_motor.position_sp = -ticks
        self.right_motor.position_sp = ticks
        self.left_motor.speed_sp = 70
        self.right_motor.speed_sp = 70
        self.left_motor.command = "run-to-abs-pos"
        self.right_motor.command = "run-to-abs-pos"
        
    def scan_turn_right(self, angle):
        self.scan_turn_left(-angle)
    
        
    def run_direct(self, left_speed, right_speed):
        self.left_motor.duty_cycle_sp = left_speed
        self.right_motor.duty_cycle_sp = right_speed
        self.left_motor.command = "run-direct"
        self.right_motor.command = "run-direct"
        
    def run_abs_dis(self, distance): # cm
        self.reset()
        ticks = (360 * distance) / (math.pi * self.diameter)
        self.left_motor.position_sp = ticks
        self.right_motor.position_sp = ticks
        self.left_motor.speed_sp = 100
        self.right_motor.speed_sp = 100
        self.left_motor.command = "run-to-abs-pos"
        self.right_motor.command = "run-to-abs-pos"
        self.left_motor.wait_until_not_moving()
        self.right_motor.wait_until_not_moving()
        
    def stop(self):
        self.left_motor.stop()
        self.right_motor.stop()
        
    def reset(self):
        self.left_motor.reset()
        self.right_motor.reset()
        
    def get_position(self):
        return (self.left_motor.position, self.right_motor.position)
        
        
    def back(self, left_speed, right_speed):
        self.run_direct(-left_speed, -right_speed)
        
    def is_running(self):
        if(self.left_motor.is_running or self.right_motor.is_running):
            return True
        else:
            return False
        