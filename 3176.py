import sys
from math import ceil, log2
from collections import deque
from typing import Tuple


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    graph = [[] for node in range(n + 1)]
    for edge in range(n - 1):
        a, b, weight = map(int, input().split())
        graph[a].append((b, weight))
        graph[b].append((a, weight))

    MAX_LOG = ceil(log2(int(1e5))) + 1
    parents = [[0] * MAX_LOG for node in range(n + 1)]
    max_road = [[0] * MAX_LOG for node in range(n + 1)]
    min_road = [[0] * MAX_LOG for node in range(n + 1)]
    depths = [0 for node in range(n + 1)]

    def bfs():
        ROOT = 1
        visit_deque = deque([ROOT])
        depths[ROOT] = 1
        while len(visit_deque):
            curr_node = visit_deque.popleft()

            for next_node, weight in graph[curr_node]:
                if depths[next_node] == 0:
                    depths[next_node] = depths[curr_node] + 1
                    parents[next_node][0] = curr_node
                    max_road[next_node][0] = weight
                    min_road[next_node][0] = weight
                    visit_deque.append(next_node)

    def set_parent():
        bfs()
        for i in range(1, MAX_LOG):
            for j in range(1, n + 1):
                parents[j][i] = parents[parents[j][i-1]][i-1]

    def set_minmax_road():
        for i in range(1, MAX_LOG):
            for j in range(1, n + 1):
                max_road[j][i] = max(max_road[j][i-1], max_road[parents[j][i-1]][i-1])
                min_road[j][i] = min(min_road[j][i-1], min_road[parents[j][i-1]][i-1])

    set_parent()
    set_minmax_road()

    def minmax_road(a: int, b: int) -> Tuple[int, int]:
        min_len, max_len = int(1e6) + 1, 0

        if depths[a] > depths[b]:
            a, b = b, a

        for i in range(MAX_LOG - 1, -1, -1):
            if depths[b] - depths[a] >= (1 << i):
                max_len = max(max_len, max_road[b][i])
                min_len = min(min_len, min_road[b][i])
                b = parents[b][i]

        if a == b:
            return min_len, max_len

        for i in range(MAX_LOG - 1, -1, -1):
            if parents[a][i] != parents[b][i]:
                max_len = max(max_len, max_road[a][i], max_road[b][i])
                min_len = min(min_len, min_road[a][i], min_road[b][i])
                a = parents[a][i]
                b = parents[b][i]

        max_len = max(max_len, max_road[a][0], max_road[b][0])
        min_len = min(min_len, min_road[a][0], min_road[b][0])

        return min_len, max_len

    k = int(input())
    for query in range(k):
        d, e = map(int, input().split())
        print(*minmax_road(d, e))


if __name__ == '__main__':
    main()
