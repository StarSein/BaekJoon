import sys
from math import ceil, log2
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    t = int(input())
    for tc in range(t):
        n = int(input())
        graph = [[] for node in range(n + 1)]
        roots = set([node for node in range(1, n + 1)])
        for edge in range(n - 1):
            a, b = map(int, input().split())
            graph[a].append(b)
            roots.discard(b)

        MAX_N = 10000
        MAX_LOG = ceil(log2(MAX_N)) + 1
        parents = [[0] * MAX_LOG for node in range(n + 1)]
        depths = [1 for node in range(n + 1)]

        def bfs():
            ROOT = roots.pop()
            visit_deque = deque([ROOT])
            while len(visit_deque):
                curr_node = visit_deque.popleft()

                for child_node in graph[curr_node]:
                    visit_deque.append(child_node)
                    depths[child_node] = depths[curr_node] + 1
                    parents[child_node][0] = curr_node

        def set_parents():
            bfs()
            for i in range(1, MAX_LOG):
                for j in range(1, n + 1):
                    parents[j][i] = parents[parents[j][i-1]][i-1]

        set_parents()

        def lca(a: int, b: int) -> int:
            if depths[a] > depths[b]:
                a, b = b, a

            for i in range(MAX_LOG - 1, -1, -1):
                if depths[b] - depths[a] >= (1 << i):
                    b = parents[b][i]

            if a == b:
                return a

            for i in range(MAX_LOG - 1, -1, -1):
                if parents[a][i] != parents[b][i]:
                    a = parents[a][i]
                    b = parents[b][i]
            return parents[a][0]

        p, q = map(int, input().split())
        print(lca(p, q))


if __name__ == '__main__':
    main()
