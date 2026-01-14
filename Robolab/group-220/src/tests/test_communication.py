#!/usr/bin/env python3

import unittest.mock
import paho.mqtt.client as mqtt
import uuid
import time
import json

from communication import Communication


class TestRoboLabCommunication(unittest.TestCase):
    @unittest.mock.patch('logging.Logger')
    def setUp(self, mock_logger):
        """
        Instantiates the communication class
        """
        client_id = '220' + str(uuid.uuid4())  # Replace YOURGROUPID with your group ID
        client = mqtt.Client(client_id=client_id,  # Unique Client-ID to recognize our program
                             clean_session=False,  # We want to be remembered
                             protocol=mqtt.MQTTv311  # Define MQTT protocol version
                             )

        # Initialize your data structure here
        self.communication = Communication(client, mock_logger, "comtest/220")
        self.test = ""

    def test_message_ready(self):
        """
        This test should check the syntax of the message type "ready"
        """
        self.communication.send_message("comtest/220", {"from": "client", "type": "ready"})
        time.sleep(1)
        self.assertEqual({"from": "debug", "type": "syntax", "payload": {"message": "Correct"}}, self.communication.test, self.communication.test)

    def test_message_path(self):
        """
        This test should check the syntax of the message type "path"
        """
        self.communication.send_message("comtest/220", {"from": "client", "type": "path", "payload": {"startX": 0, "startY": 0, "startDirection": 0, "endX": 0, "endY": 0, "endDirection": 0, "pathStatus": "free"}})
        time.sleep(1)
        self.assertEqual({"from": "debug", "type": "syntax", "payload": {"message": "Correct"}}, self.communication.test, self.communication.test)

    def test_message_path_invalid(self):
        """
        This test should check the syntax of the message type "path" with errors/invalid data
        """
        self.communication.send_message("comtest/220", {"from": "client", "type": "path", "payload": {"startX": 0, "startY": 0, "startDirection": 0, "endX": 0, "endY": 0, "endDirection": 0, "pathStatus": ""}})
        time.sleep(1)
        self.assertEqual({'from': 'debug', 'type': 'syntax', 'payload': {'message': 'Incorrect', 'errors': ['Unknown path status: ']}}, self.communication.test, self.communication.test)

    def test_message_select(self):
        """
        This test should check the syntax of the message type "pathSelect"
        """
        self.communication.send_message("comtest/220", {"from": "client", "type": "pathSelect", "payload": {"startX": 0, "startY": 0, "startDirection": 0}})
        time.sleep(1)
        self.assertEqual({"from": "debug", "type": "syntax", "payload": {"message": "Correct"}}, self.communication.test, self.communication.test)

    def test_message_complete(self):
        """
        This test should check the syntax of the message type "explorationCompleted" or "targetReached"
        """
        self.communication.send_message("comtest/220", {"from": "client", "type": "explorationCompleted", "payload": {"message": "test"}})
        time.sleep(1)
        self.assertEqual({"from": "debug", "type": "syntax", "payload": {"message": "Correct"}}, self.communication.test, self.communication.test)

    def on_message(self, client, data, message):
        payload = json.loads(message.payload.decode('utf-8'))
        if payload["from"] == "debug" and payload["type"] == "syntax":
            self.test = payload


if __name__ == "__main__":
    unittest.main()
