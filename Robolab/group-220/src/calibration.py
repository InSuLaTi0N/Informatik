from sensor import ColorSensor
from linefollower import LineFollower

    
class Calibration:
    
    """
    calibrate red and blue node
    test white&black luminance
    """
    
    def calibrate(self):
        input("Please take the robot on red:")
        red = ColorSensor().get_rgb()
        ColorSensor().set_red(red)
        
        input("Please take the robot on blue:")
        blue = ColorSensor().get_rgb()
        ColorSensor().set_blue(blue)
        
        input("Please take the robot on blackline:")
        black_lum = ColorSensor().get_luminance()
        print(black_lum)
        
        input("Please take the robot on white:")
        white_lum = ColorSensor().get_luminance()
        print(white_lum)
        
        input("finished")
        
        return (white_lum - black_lum) / 2
        
    