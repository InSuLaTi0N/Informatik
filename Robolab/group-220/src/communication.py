#!/usr/bin/env python3

# Attention: Do not import the ev3dev.ev3 module in this file
import json
import platform
import ssl

# Fix: SSL certificate problem on macOS
if all(platform.mac_ver()):
    from OpenSSL import SSL


class Communication:
    """
    Class to hold the MQTT client communication
    Feel free to add functions and update the constructor to satisfy your requirements and
    thereby solve the task according to the specifications
    """

    def __init__(self, mqtt_client, logger, topic):
        """
        Initializes communication module, connect to server, subscribe, etc.
        :param mqtt_client: paho.mqtt.client.Client
        :param logger: logging.Logger
        """
        # DO NOT CHANGE THE SETUP HERE
        self.client = mqtt_client
        self.client.tls_set(tls_version=ssl.PROTOCOL_TLS)
        self.client.on_message = self.safe_on_message_handler
        # Add your client setup here
        self.client.on_connect = self.on_connect
        self.client.on_log = self.on_log  # Callback to receive ping request
        self.client.username_pw_set('220', password='nBkL8zAVUq')
        self.client.connect('mothership.inf.tu-dresden.de', port=8883, keepalive=3)  # keepalive sets the timeout for a ping request
        self.channel = topic  # variable to dynamically change the subscription topic
        self.client.subscribe(self.channel)  # subscribe to this topic
        self.logger = logger
        self.planet_name = ""  # variable to subscribe to the specific planet topic
        self.planet_is_done = False  # variable to efficiently send and receive messages in the least time possible
        self.path_is_done = False  # variable to efficiently send and receive messages in the least time possible
        self.pathSelect_is_done = False  # variable to efficiently send and receive messages in the least time possible and timeout the programm after 3 seconds of no send or received messages
        self.done = False  # variable to end the while loop in the main.py
        self.path = {  # standard declaration for path
            "from": "client",
            "type": "path",
            "payload": {
                "startX": 0,
                "startY": 0,
                "startDirection": 0,
                "endX": 0,
                "endY": 0,
                "endDirection": 0,
                "pathStatus": "free",
                "pathWeight": 0
            }
        }
        self.pathSelect = {  # standard declaration for pathSelect
            "from": "client",
            "type": "pathSelect",
            "payload": {
                "startX": 0,
                "startY": 0,
                "startDirection": 0
            }
        }
        self.pathUnveiled = []  # standard declaration for pathUnveiled
        self.target = {"from": "server", "type": "target", "payload": {"targetX": None, "targetY": None}}  # standard declaration for target
        self.test = {}  # variable for unit tests
        self.client.loop_start()  # start the loop of the callbacks

    def on_log(self, client, userdata, level, buf):  # sets pathSelect_is_done to True if no messages were send or received in 3 seconds
        if not self.pathSelect_is_done:
            if str(buf) == "Sending PINGREQ":
                self.pathSelect_is_done = True

    # DO NOT EDIT THE METHOD SIGNATURE
    def on_message(self, client, data, message):  # handles the incoming massages
        """
        Handles the callback if any message arrived
        :param client: paho.mqtt.client.Client
        :param data: Object
        :param message: Object
        :return: void
        """
        payload = json.loads(message.payload.decode('utf-8'))
        self.logger.debug(json.dumps(payload, indent=2))

        # YOUR CODE FOLLOWS (remove pass, please!)

        if payload["from"] == "server" and payload["type"] == "planet":
            self.planet_name = payload["payload"]["planetName"]
            self.path["payload"]["startX"] = payload["payload"]["startX"]
            self.path["payload"]["startY"] = payload["payload"]["startY"]
            self.path["payload"]["startDirection"] = payload["payload"]["startOrientation"]
            self.path["payload"]["endX"] = payload["payload"]["startX"]
            self.path["payload"]["endY"] = payload["payload"]["startY"]
            self.path["payload"]["endDirection"] = (payload["payload"]["startOrientation"] - 180) % 360
            self.planet_is_done = True
            print("Message from type \"" + str(payload["type"]) + "\" by \"" + str(payload["from"]) + "\" received: " + str(payload))
        elif payload["from"] == "server" and payload["type"] == "path":
            self.path["payload"]["pathWeight"] = payload["payload"]["pathWeight"]
            self.path_is_done = True
            print("Message from type \"" + str(payload["type"]) + "\" by \"" + str(payload["from"]) + "\" received: " + str(payload))
        elif payload["from"] == "server" and payload["type"] == "pathSelect":
            self.pathSelect["payload"]["startDirection"] = payload["payload"]["startDirection"]
            print("Message from type \"" + str(payload["type"]) + "\" by \"" + str(payload["from"]) + "\" received: " + str(payload))
        elif payload["from"] == "server" and payload["type"] == "pathUnveiled":
            self.pathUnveiled.append(payload)
            print("Message from type \"" + str(payload["type"]) + "\" by \"" + str(payload["from"]) + "\" received: " + str(payload))
        elif payload["from"] == "server" and payload["type"] == "target":
            self.target = payload
            print("Message from type \"" + str(payload["type"]) + "\" by \"" + str(payload["from"]) + "\" received: " + str(payload))
        elif payload["from"] == "server" and payload["type"] == "done":
            self.done = True
            print("Message from type \"" + str(payload["type"]) + "\" by \"" + str(payload["from"]) + "\" received: " + str(payload))
        elif payload["from"] == "debug" and payload["type"] == "syntax":
            self.test = payload

    def on_connect(self, client, userdata, flags, rc):  # is just there to inform you tht the connection to the server was successful
        print("Connection established...")

    # DO NOT EDIT THE METHOD SIGNATURE
    #
    # In order to keep the logging working you must provide a topic string and
    # an already encoded JSON-Object as message.
    def send_message(self, topic, message):  # handles messages that have to be send
        """
        Sends given message to specified channel
        :param topic: String
        :param message: Object
        :return: void
        """
        self.logger.debug('Send to: ' + topic)
        self.logger.debug(json.dumps(message, indent=2))

        # YOUR CODE FOLLOWS (remove pass, please!)
        if message["type"] == "ready":
            self.client.publish(topic, json.dumps(message))
        elif message["type"] == "path":
            self.client.publish(topic, json.dumps(message))
        elif message["type"] == "pathSelect":
            self.client.publish(topic, json.dumps(message))
        elif message["type"] == "targetReached":
            self.client.publish(topic, json.dumps(message))
        elif message["type"] == "explorationCompleted":
            self.client.publish(topic, json.dumps(message))

    # DO NOT EDIT THE METHOD SIGNATURE OR BODY
    #
    # This helper method encapsulated the original "on_message" method and handles
    # exceptions thrown by threads spawned by "paho-mqtt"
    def safe_on_message_handler(self, client, data, message):
        """
        Handle exceptions thrown by the paho library
        :param client: paho.mqtt.client.Client
        :param data: Object
        :param message: Object
        :return: void
        """
        try:
            self.on_message(client, data, message)
        except:
            import traceback
            traceback.print_exc()
            raise

    def ready(self):  # function the send a ready message
        message = {
            "from": "client",
            "type": "ready"
        }
        self.send_message("explorer/220", message)
        while not self.planet_is_done:  # makes the program wait for the server potential response
            pass

    def new_path(self, xe, ye, de, status):  # function the send a path message
        self.path_is_done = False
        message = {
            "from": "client",
            "type": "path",
            "payload": {
                "startX": self.path["payload"]["endX"],
                "startY": self.path["payload"]["endY"],
                "startDirection": self.pathSelect["payload"]["startDirection"],
                "endX": xe,
                "endY": ye,
                "endDirection": de,
                "pathStatus": status
            }
        }
        self.path = message
        self.channel = "planet/" + self.planet_name + "/220"
        self.client.subscribe(self.channel)
        self.send_message(self.channel, message)
        while not self.path_is_done:  # makes the program wait for the server potential response
            pass

    def path_select(self, d):  # function the send a selectPath message
        self.pathSelect_is_done = False
        message = {
            "from": "client",
            "type": "pathSelect",
            "payload": {
                "startX": self.path["payload"]["endX"],
                "startY": self.path["payload"]["endY"],
                "startDirection": d
            }
        }
        self.pathSelect = message
        self.send_message(self.channel, message)
        while not self.pathSelect_is_done:  # makes the program wait for the server potential response
            pass

    def target_reached(self):  # function the send a targetReached message
        message = {
            "from": "client",
            "type": "targetReached",
            "payload": {
                "message": "test"
            }
        }
        self.done = True  # sets the variable that ends the while loop in the main.py to true
        self.channel = "explorer/220"  # changes to channel because the message is transmitted on another topic
        self.client.subscribe(self.channel)
        self.send_message(self.channel, message)

    def exploration_completed(self):  # function the send a explorationCompleted message
        message = {
            "from": "client",
            "type": "explorationCompleted",
            "payload": {
                "message": "test"
            }
        }
        self.done = True  # sets the variable that ends the while loop in the main.py to true
        self.channel = "explorer/220"  # changes to channel because the message is transmitted on another topic
        self.client.subscribe(self.channel)
        self.send_message(self.channel, message)
