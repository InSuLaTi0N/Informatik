from typing import List, Tuple, Union, Dict
from enum import IntEnum, unique


@unique
class Direction(IntEnum):
    """ Directions in shortcut """
    NORTH = 0
    EAST = 90
    SOUTH = 180
    WEST = 270


class Dijkstra:
    # graph = {'a': {'b': 10, 'c': 3}, 'b': {'c': 1, 'd': 2}, 'c': {'b': 4, 'd': 8, 'e': 2}, 'd': {'e': 7}, 'e': {'d': 9}}
    # graph1 = {(1,1): {(1,2): 2, (1,3): 6}, (1,2): {(1,3): 3}}
    # Test

    # collection_of_paths: Dict[Tuple[int, int], Dict[Direction, Tuple[Tuple[int, int], Direction, Weight]]] = {}
    def create_graph(self, collection_of_paths):
        return_graph = {}  # mapping (nodes, direction) to (childNode, direction)-Weight Pairs
        # print(collection_of_paths)
        for key, value in collection_of_paths.items():
            nodes = {}  # mapping childNodes to weight
            for k, v in value.items():
                nodes[v[0]] = v[2]
            return_graph[key] = nodes
        return return_graph

    # return_graph = {((1,1), Nord) : {(1,2),Nord) : 5 , (1,3),Süd) : 12}, ((1,2), Nord) : {((1,3), Süd) : 6},
    #                ((1,3), Nord) : {}}

    def algorithm(self, collection_of_paths, start, goal) -> Union[None, List[Tuple[Tuple[int, int], Direction]]]:
        graph = self.create_graph(collection_of_paths) #erstellt einen graphen mit "geordneteren nodes"
        #print(graph)
        shortest_distance = {}
        predecessor = {}
        unseen_nodes = list(graph.keys())  # list unbesuchter knoten
        infinity = float("inf")
        path = []
        for node in unseen_nodes:
            shortest_distance[node] = infinity  # kosten aller knoten auf inf.
        shortest_distance[start] = 0  # kosten der startnode auf 0
        # print(shortest_distance)

        while unseen_nodes:  # unseen_nodes != empty
            min_node = None
            for node in unseen_nodes:       #finde node mit geringsten kosten
                if min_node is None:
                    min_node = node
                elif shortest_distance[node] < shortest_distance[min_node]:
                    min_node = node

            # print(shortest_distance)
            for childNode, weight in graph[min_node].items(): #gehe dict von childnodes:weights für actuelle node durch
                # print(childNode)
                # print(shortest_distance)
                if weight + shortest_distance[min_node] < shortest_distance[childNode]: #überschreibe wert falls geringeres
                    shortest_distance[childNode] = weight + shortest_distance[min_node] #weight erreicht wird
                    predecessor[childNode] = min_node   #notiere vorgänger der node für shortest_path -> backtracking zur pfadfindung
            unseen_nodes.remove(min_node)
        # print(shortest_distance)
        # print(predecessor)

        current_node = goal
        while current_node != start:
            try:
                path.insert(0, current_node)
                current_node = predecessor[current_node]
            except KeyError:
                #print("Path not reachable")
                return None
        path.insert(0, start)
        if shortest_distance[goal] != infinity:
            #print("Shortest distance :" + str(shortest_distance[goal]))
            #print("And the path is :" + str(path))
            pass

        """return_list = []
        length_of_path = len(path)
        print(path)
        while length_of_path > 1:
            for k,v in collection_of_paths.get(path[length_of_path-2]).items():
                if v[0] == path[length_of_path-1]:
                    return_list.insert(0, (path[length_of_path-1], v[1]))
            length_of_path -= 1
        return return_list"""

        return_list = []                #liste von directions -> nodes nicht wichtig für brick
        length_of_path = len(path)
        print(path)
        while length_of_path > 1:
            for k,v in collection_of_paths.get(path[length_of_path-2]).items():
                if v[0] == path[length_of_path-1]:
                    return_list.insert(0, k)
            length_of_path -= 1
        return return_list

