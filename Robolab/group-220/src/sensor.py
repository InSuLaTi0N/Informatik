import ev3dev.ev3 as ev3

class ColorSensor:
    
    def __init__(self):
        self.color_sensor = ev3.ColorSensor()
        self.color_sensor.mode = "RGB-RAW"
        self.red = (109, 51, 16)
        self.blue = (32, 142, 90)
        
    # return current rgb values
    def get_rgb(self):
        rgb = self.color_sensor.bin_data("hhh") # between 0 - 1020
        return rgb
    
    # calculate the luminance
    def get_luminance(self):
        rgb = self.get_rgb()
        luminance = 0.2126 * pow((rgb[0] / 400), 2.2) + \
            0.7152 * pow((rgb[1] / 400), 2.2) + \
            0.0722 * pow((rgb[2] / 400), 2.2)
            
        return luminance
    
    # check black line
    def is_black(self):
        black_lum = 0.05
        if(self.get_luminance() < black_lum):
            return True
        else:
            return False
    
    # check red point
    def is_red(self):
        range = 30
        rgb = self.get_rgb()
        if((self.red[0] - range) < rgb[0] < (self.red[0] + range) and \
            (self.red[1] - range) < rgb[1] < (self.red[1] + range) and \
            (self.red[2] - range) < rgb[2] < (self.red[2] + range)):
            return True
        else:
            return False
        
    # check blue point
    def is_blue(self):
        range = 30
        rgb = self.get_rgb()
        if((self.blue[0] - range) < rgb[0] < (self.blue[0] + range) and \
            (self.blue[1] - range) < rgb[1] < (self.blue[1] + range) and \
            (self.blue[2] - range) < rgb[2] < (self.blue[2] + range)):
            return True
        else:
            return False
        
    # calibration 
    def set_red(self, red):
        self.red = red
        
    def set_blue(self, blue):
        self.blue = blue
        
class TouchSensor:
    
    def __init__(self):
        self.touch_sensor = ev3.TouchSensor()
        
    def touched(self):
        if(self.touch_sensor.is_pressed):
            return True
        else:
            return False