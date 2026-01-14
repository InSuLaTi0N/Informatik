#!/usr/bin/env python3

import unittest
from planet import Direction, Planet


class ExampleTestPlanet(unittest.TestCase):
    def setUp(self):
        """
        Instantiates the planet data structure and fills it with paths

        +--+
        |  |
        +-0,3------+
           |       |
          0,2-----2,2 (target)
           |      /
        +-0,1    /
        |  |    /
        +-0,0-1,0
           |
        (start)

        """
        # Initialize your data structure here
        #self.planet = Planet()
        #self.planet.add_path(((0, 0), Direction.NORTH), ((0, 1), Direction.SOUTH), 1)
        #self.planet.add_path(((0, 1), Direction.WEST), ((0, 0), Direction.WEST), 1)

    @unittest.skip('Example test, should not count in final test results')
    def test_target_not_reachable_with_loop(self):
        """
        This test should check that the shortest-path algorithm does not get stuck in a loop between two points while
        searching for a target not reachable nearby

        Result: Target is not reachable
        """
        self.assertIsNone(self.planet.shortest_path((0, 0), (1, 2)))


class TestRoboLabPlanet(unittest.TestCase):
    def setUp(self):
        """
        Instantiates the planet data structure and fills it with paths

        MODEL YOUR TEST PLANET HERE (if you'd like):

        """
        # Initialize your data structure here
        self.planet = Planet()
        self.planet.add_path(((1, 1), Direction(90)), ((1, 2), Direction(0)), 5)
        self.planet.add_path(((1, 1), Direction(180)), ((1, 3), Direction(0)), 13)
        self.planet.add_path(((1, 1), Direction(270)), ((1, 4), Direction(0)), 20)
        self.planet.add_path(((1, 2), Direction(90)), ((1, 3), Direction(180)), 7)
        self.planet.add_path(((1, 3), Direction(90)), ((1, 4), Direction(90)), 8)

    def test_integrity(self):
        """
        This test should check that the dictionary returned by "planet.get_paths()" matches the expected structure
        """
        # check key
        check_key = list(self.planet.get_paths.keys())[0]
        self.assertIsInstance(check_key, tuple, "wrong key type")  # check key-type: tuple
        self.assertIsInstance(check_key[0], int, "wrong tuple type")  # check tuple-type[0]: int
        self.assertIsInstance(check_key[1], int, "wrong tuple type")  # check tuple-type[1]: int
        self.assertEqual(len(check_key), 2, "tuple should have 2 values")  # check tuple-length
        # check value
        check_dict = self.planet.get_paths.get((1, 1))
        self.assertIsInstance(check_dict, dict, "value should be dict")  # check value-type: dict
        check_key_2 = list(check_dict.keys())[0]
        self.assertIsInstance(check_key_2, Direction, "wrong key_2 type")  # check key2-type: direction
        check_value_2 = self.planet.get_paths.get((1, 1)).get(Direction(90))  # check value2-type: tuple
        self.assertIsInstance(check_value_2[0], tuple, "wrong tuple type")  # check tuple2-type[0]: tuple
        self.assertIsInstance(check_value_2[1], Direction, "wrong tuple type")  # check tuple2-type[1]: Direction
        self.assertIsInstance(check_value_2[2], int, "wrong tuple type")  # check tuple2-type[2]: int
        self.assertIsInstance(check_value_2[0][0], int, "wrong tuple type")  # check tuple3-type[0]: int
        self.assertIsInstance(check_value_2[0][1], int, "wrong tuple type")  # check tuple3-type[1]: int
        self.assertEqual(len(check_value_2), 3, "wrong tuple length")  # check tuple2-length
        self.assertEqual(len(check_value_2[0]), 2, "wrong tuple length")  # check tuple3-length

        self.assertEqual(self.planet.get_paths.get((1, 1)), {Direction(90) : ((1, 2), Direction(0), 5),
                                                             Direction(180): ((1, 3), Direction(0),13),
                                                             Direction(270): ((1, 4), Direction(0), 20)})

    def test_empty_planet(self):
        """
        This test should check that an empty planet really is empty
        """
        empty_planet = Planet()
        self.assertEqual(len(empty_planet.collection_of_paths), 0, "planet should be empty")

    def test_target(self):
        """
        This test should check that the shortest-path algorithm implemented works.

        Requirement: Minimum distance is three nodes (two paths in list returned)
        """
        self.assertEqual(self.planet.shortest_path((1, 1), (1, 3)), [Direction(90), Direction(90)],
                                "should find shortest path")

    def test_target_not_reachable(self):
        """
        This test should check that a target outside the map or at an unexplored node is not reachable
        """
        print(self.planet.get_paths)
        self.assertIsNone(self.planet.shortest_path((1, 1), (1, 5)), "should not find any path")

    def test_same_length(self):
        """
        This test should check that the shortest-path algorithm implemented returns a shortest path even if there
        are multiple shortest paths with the same length.

        Requirement: Minimum of two paths with same cost exists, only one is returned by the logic implemented
        """
        self.assertEqual(len(self.planet.shortest_path((1, 1), (1, 4))), 1, "should only return one path")

    def test_target_with_loop(self):
        """
        This test should check that the shortest-path algorithm does not get stuck in a loop between two points while
        searching for a target nearby

        Result: Target is reachable
        """
        self.assertTrue(len(self.planet.shortest_path((1, 1), (1, 2))) >= 1, "algorithm should find any path")

    def test_target_not_reachable_with_loop(self):
        """
        This test should check that the shortest-path algorithm does not get stuck in a loop between two points while
        searching for a target not reachable nearby

        Result: Target is not reachable
        """
        self.assertIsNone(self.planet.shortest_path((1, 1), (1, 5)), "algorithm should return 'None'")


if __name__ == "__main__":
    unittest.main()
