#!/usr/bin/env python3

# Attention: Do not import the ev3dev.ev3 module in this file
from enum import IntEnum, unique
from typing import List, Tuple, Dict, Union, Type
from dijkstra import Dijkstra

@unique
class Direction(IntEnum):
    """ Directions in shortcut """
    NORTH = 0
    EAST = 90
    SOUTH = 180
    WEST = 270


Weight = int

"""
Weight of a given path (received from the server)

Value:  -1 if blocked path
        >0 for all other paths
        never 0
"""
collection_of_paths: Dict[Tuple[int, int], Dict[Direction, Tuple[Tuple[int, int], Direction, int]]] = {}


class Planet:
    """
    Contains the representation of the map and provides certain functions to manipulate or extend
    it according to the specifications
    """
    collection_of_paths: Dict[Tuple[int, int], Dict[Direction, Tuple[Tuple[int, int], Direction, int]]] = {}

    def __init__(self):
        """ Initializes the data structure """
        self.target = None

    def add_path(self, start: Tuple[Tuple[int, int], Direction], target: Tuple[Tuple[int, int], Direction],
                 weight):
        """
         Adds a bidirectional path defined between the start and end coordinates to the map and assigns the weight to it

        Example:
            add_path(((0, 3), Direction.NORTH), ((0, 3), Direction.WEST), 1)
        :param start: 2-Tuple
        :param target:  2-Tuple
        :param weight: Integer
        :return: void
        """

        # YOUR CODE FOLLOWS (remove pass, please!)
        if start[0] in collection_of_paths.keys() and target[0] in collection_of_paths.keys():
            collection_of_paths.get(start[0])[start[1]] = (target[0], target[1], weight)
            collection_of_paths.get(target[0])[target[1]] = (start[0], start[1], weight)
        elif start[0] in collection_of_paths.keys() and target[0] not in collection_of_paths.keys():
            collection_of_paths.get(start[0])[start[1]] = (target[0], target[1], weight)
            collection_of_paths[target[0]] = {target[1]: (start[0], start[1], weight)}
        elif start[0] not in collection_of_paths.keys() and target[0] in collection_of_paths.keys():
            collection_of_paths.get(target[0])[target[1]] = (start[0], start[1], weight)
            collection_of_paths[start[0]] = {start[1]: (target[0], target[1], weight)}
        else:   #both not in collection
            collection_of_paths[start[0]] = {start[1]: (target[0], target[1], weight)}
            collection_of_paths[target[0]] = {target[1]: (start[0], start[1], weight)}

        #liest alle pfade alls gerichtete pfade ein
        #und fügt damit auch immer das entgegengesetzte paar hinzu

    @property
    def get_paths(self) -> Dict[Tuple[int, int], Dict[Direction, Tuple[Tuple[int, int], Direction, Weight]]]:
        """
        Returns all paths

        Example:
            {
                (0, 3): {
                    Direction.NORTH: ((0, 3), Direction.WEST, 1),
                    Direction.EAST: ((1, 3), Direction.WEST, 2),
                    Direction.WEST: ((0, 3), Direction.NORTH, 1)
                },
                (1, 3): {
                    Direction.WEST: ((0, 3), Direction.EAST, 2),
                    ...
                },
                ...
            }
        :return: Dict
        """

        # YOUR CODE FOLLOWS (remove pass, please!)s
        print("{")
        for key, value in collection_of_paths.items():
            print("\t", key, ": {")
            for k, v in value.items():
                print("\t\t", k, "\t: ", v)
            print("\t }")
        print("}")
        return collection_of_paths

    def shortest_path(self, start: Tuple[int, int], target: Tuple[int, int]) -> Union[
        None, List[Tuple[Tuple[int, int], Direction]]]:
        """
        Returns a shortest path between two nodes

        Examples:
            shortest_path((0,0), (2,2)) returns: [((0, 0), Direction.EAST), ((1, 0), Direction.NORTH)]
            shortest_path((0,0), (1,2)) returns: None
        :param start: 2-Tuple
        :param target: 2-Tuple
        :return: 2-Tuple[List, Direction]
        """

        # YOUR CODE FOLLOWS (remove pass, please!)
        find_shortest_path = Dijkstra()
        return find_shortest_path.algorithm(collection_of_paths, start, target)

"""planetA = Planet()

#collection_of_paths: Dict[Tuple[int, int], Dict[Direction, Tuple[Tuple[int, int], Direction, Weight]]] = {}
planetA.add_path(((1, 1), Direction(180)), ((1, 2), Direction(90)), 5)
planetA.add_path(((1, 1), Direction(90)), ((1, 3), Direction(90)), 13)
#planetA.add_path(((1, 1), Direction(180)), ((1, 4), Direction(180)), 20)
planetA.add_path(((1, 2), Direction(270)), ((1, 3), Direction(270)), 7)
planetA.add_path(((1, 3), Direction(0)), ((1, 4), Direction(0)), 8)

#print(collection_of_paths)
# planetA.get_paths()
# graphA = generatorA.create_graph(collection_of_paths)
# print(graphA)
# dijkstraA.algorithm(graphA, (1, 1), (1, 3))

# graph1 = {(1,1): {(1,2): 2, (1,3): 6}, (1,2): {(1,3): 3}, (1,3): {}}

dijkstraA = Dijkstra()
dijkstraA.algorithm(collection_of_paths, (1, 1), (1, 4))"""