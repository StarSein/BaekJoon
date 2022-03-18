import sys
from math import log2, ceil
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    graph = [[] for node in range(n + 1)]
    for edge in range(n - 1):
        a, b, weight = map(int, input().split())
        graph[a].append((b, weight))
        graph[b].append((a, weight))

    MAX_N = 40000
    MAX_NUM_PARENTS = ceil(log2(MAX_N)) + 1
    parents = [[0] * MAX_NUM_PARENTS for node in range(n + 1)]
    depths = [0 for node in range(n + 1)]
    dp = [0 for node in range(n + 1)]
    ROOT = 1

    def bfs():
        visited = [False] * (n + 1)
        visit_deque = deque([(ROOT, 1)])
        visited[ROOT] = True
        while len(visit_deque):
            curr_node, curr_depth = visit_deque.popleft()
            depths[curr_node] = curr_depth

            for next_node, weight in graph[curr_node]:
                if not visited[next_node]:
                    visit_deque.append((next_node, curr_depth + 1))
                    visited[next_node] = True
                    parents[next_node][0] = curr_node
                    dp[next_node] = dp[curr_node] + weight

    def set_parent():
        bfs()
        for i in range(1, MAX_NUM_PARENTS):
            for j in range(1, n + 1):
                parents[j][i] = parents[parents[j][i-1]][i-1]

    set_parent()

    def lca(a: int, b: int) -> int:
        if depths[a] > depths[b]:
            a, b = b, a

        for i in range(MAX_NUM_PARENTS - 1, -1, -1):
            if depths[b] - depths[a] >= (1 << i):
                b = parents[b][i]

        if a == b:
            return a

        for i in range(MAX_NUM_PARENTS - 1, -1, -1):
            if parents[a][i] != parents[b][i]:
                a = parents[a][i]
                b = parents[b][i]
        return parents[a][0]

    def dist(a: int, b: int) -> int:
        c = lca(a, b)
        return dp[a] + dp[b] - 2 * dp[c]

    m = int(input())
    for query in range(m):
        a, b = map(int, input().split())
        print(dist(a, b))


if __name__ == '__main__':
    main()
