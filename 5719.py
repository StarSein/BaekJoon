import sys
import heapq
from collections import deque


INF = int(10e7)
NONE = -1


def input():
    return sys.stdin.readline().rstrip()


def main():
    def dijkstra(start: int, end: int):
        heap = [(0, NONE, start)]
        min_route = [INF] * n
        while len(heap):
            current_cost, recent_node, current_node = heapq.heappop(heap)
            if current_cost > min_route[current_node]:
                if current_node == end:
                    return min_route[current_node]
                else:
                    continue

            min_route[current_node] = current_cost
            recent_list[current_node].add(recent_node)

            for next_node, weight in connected[current_node].items():
                if min_route[next_node] == INF:
                    heapq.heappush(heap, (current_cost + weight, current_node, next_node))
        if min_route[end] == INF:
            return -1
        else:
            return min_route[end]

    def erase_short_path():
        queue = deque([d])
        while len(queue):
            current_node = queue.popleft()

            if current_node == s:
                continue

            for recent_node in recent_list[current_node].copy():
                connected[recent_node].pop(current_node)
                recent_list[current_node].discard(recent_node)
                queue.append(recent_node)

    while True:
        n, m = map(int, input().split())
        if n == 0:
            return
        s, d = map(int, input().split())
        connected = [dict() for node in range(n)]
        for road in range(m):
            u, v, p = map(int, input().split())
            connected[u][v] = p
        recent_list = [set() for node in range(n)]
        best_route = dijkstra(s, d)
        if best_route == -1:
            print(best_route)
            continue

        erase_short_path()

        second_route = dijkstra(s, d)
        print(second_route)


main()
