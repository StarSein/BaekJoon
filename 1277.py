import sys
import heapq
from math import sqrt


input = sys.stdin.readline
INF = 200_000
EMPTY_SPOT = (-1, -1)
X = 0
Y = 1


def solution() -> float:
    graph = [[INF for y in range(n + 1)] for x in range(n + 1)]
    for i in range(1, n + 1):
        for j in range(i + 1, n + 1):
            dist = sqrt((sites[i][X] - sites[j][X])**2 + (sites[i][Y] - sites[j][Y])**2)
            graph[i][j] = dist
            graph[j][i] = dist
    for a, b in is_cable:
        graph[a][b] = 0
        graph[b][a] = 0

    is_visited = [False] * (n + 1)
    START = 1
    FINISH = n
    heap = [(0, START)]
    while len(heap):
        dist, current = heapq.heappop(heap)
        if is_visited[current]:
            continue

        is_visited[current] = True
        if current == FINISH:
            return dist

        for next_node, weight in enumerate(graph[current]):
            if weight <= m:
                heapq.heappush(heap, (dist + weight, next_node))


if __name__ == '__main__':
    n, w = map(int, input().split())
    m = float(input())
    sites = [EMPTY_SPOT]
    for i in range(n):
        sites.append(tuple(map(int, input().split())))
    is_cable = []
    for j in range(w):
        is_cable.append(tuple(map(int, input().split())))
    sol = solution()
    print(int(1000 * sol))
