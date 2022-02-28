import sys
import heapq


NONE = -1


def input():
    return sys.stdin.readline().rstrip()


def main():
    def dijkstra(start: int, end: int):
        heap = [(0, NONE, start)]
        is_visited = [False] * n
        while len(heap):
            current_cost, recent_node, current_node = heapq.heappop(heap)
            if is_visited[current_node]:
                continue

            recent_list[current_node] = recent_node
            if current_node == end:
                return current_cost
            is_visited[current_node] = True

            for next_node, weight in connected[current_node].items():
                heapq.heappush(heap, (current_cost + weight, current_node, next_node))
        return -1

    def erase_short_path():
        current_node = d
        while current_node != s:
            recent_node = recent_list[current_node]
            connected[recent_node].pop(current_node)
            current_node = recent_node

    while True:
        n, m = map(int, input().split())
        if n == 0:
            return
        s, d = map(int, input().split())
        connected = [dict() for node in range(n)]
        for road in range(m):
            u, v, p = map(int, input().split())
            connected[u][v] = p
        recent_list = [NONE] * n
        best_route = dijkstra(s, d)
        if best_route == -1:
            print(best_route)
            continue
            
        erase_short_path()
        while True:
            recent_list = [NONE] * n
            second_route = dijkstra(s, d)
            if second_route > best_route or second_route == -1:
                print(second_route)
                break
            erase_short_path()


main()
