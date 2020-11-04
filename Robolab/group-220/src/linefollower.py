from sensor import ColorSensor, TouchSensor
from motor import Motor

class LineFollower:
    
    def __init__(self, offset = 0.38, kp = 24, ki = 2.1, kd = 5, tp = 25):
        # kc = 40
        self.motor = Motor()
        self.kp = kp
        self.ki = ki
        self.kd = kd
        self.tp = tp
        self.offset = offset
        self.intergral = 0
        self.lasterror = 0
        self.derivative = 0
        
    def line_follow(self):
        luminance = ColorSensor().get_luminance()
        # print(f"lum: {luminance}")
        error = luminance - self.offset
        # print(f"error: {error}")
        self.intergral += error
        # print(f"integral: {self.intergral}")
        if(error * self.lasterror) < 0:
            self.intergral = 0
        self.derivative = error - self.lasterror
        # print(f"derivate: {self.derivative}")
        turn = self.kp * error + self.ki * self.intergral + self.kd * self.derivative
        # print(f"turn: {turn}")
        self.motor.run_direct(self.tp + turn, self.tp - turn)
        self.lasterror = error
        
    def blocked(self):
        if(TouchSensor().touched()):
            self.motor.reset()
            self.motor.run_abs_dis(-5)
            self.motor.turn_right(45)
            while not ColorSensor().is_black():
                self.motor.run_direct(25, -25)
            self.motor.stop()
            return True