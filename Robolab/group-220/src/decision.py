from planet import Direction

class Decision:
    def find_open_path(self, open_directions, past_paths, current_node, planet):
        #suche kürzesten weg zu einer node, die noch offene pfade hat
        for o in reversed(open_directions):
            if o not in past_paths:
                print("Test")
                print("#######" + str(planet.shortest_path(current_node[0], o[0])[0]) + "#######")
                return planet.shortest_path(current_node[0], o[0])[0]

    def select_direction(self, end, node, open_dir, past_paths, planet):  # Richtungsauswahl, prio rechts-mitte-links
        if end == Direction(180):
            if (node[0], Direction(270)) in open_dir and (node[0], Direction(270)) not in past_paths:
                return 270
            elif (node[0], Direction(180)) in open_dir and (node[0], Direction(180)) not in past_paths:
                return 180
            elif (node[0], Direction(90)) in open_dir and (node[0], Direction(90)) not in past_paths:
                return 90
            else:
                return self.find_open_path(open_dir, past_paths, node, planet)
        elif end == Direction(270):
            if (node[0], Direction(0)) in open_dir and (node[0], Direction(0)) not in past_paths:
                return 0
            elif (node[0], Direction(270)) in open_dir and (node[0], Direction(270)) not in past_paths:
                return 270
            elif (node[0], Direction(180)) in open_dir and (node[0], Direction(180)) not in past_paths:
                return 180
            else:
                return self.find_open_path(open_dir, past_paths, node, planet)
        elif end == Direction(0):
            if (node[0], Direction(90)) in open_dir and (node[0], Direction(90)) not in past_paths:
                return 90
            elif (node[0], Direction(0)) in open_dir and (node[0], Direction(0)) not in past_paths:
                return 0
            elif (node[0], Direction(270)) in open_dir and (node[0], Direction(270)) not in past_paths:
                return 270
            else:
                return self.find_open_path(open_dir, past_paths, node, planet)
        else:
            if (node[0], Direction(180)) in open_dir and (node[0], Direction(180)) not in past_paths:
                return 180
            elif (node[0], Direction(90)) in open_dir and (node[0], Direction(90)) not in past_paths:
                return 90
            elif (node[0], Direction(0)) in open_dir and (node[0], Direction(0)) not in past_paths:
                return 0
            else:
                return self.find_open_path(open_dir, past_paths, node, planet)