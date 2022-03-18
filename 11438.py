import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    graph = [[] for node in range(n + 1)]
    for edge in range(n - 1):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    depths = [0 for node in range(n + 1)]
    MAX_NUM_PARENTS = 21
    parents = [[0] * MAX_NUM_PARENTS for node in range(n + 1)]

    def bfs():
        ROOT = 1
        depth = 1
        visit_list = [False] * (n + 1)
        visit_list[ROOT] = True
        visit_deque = deque([(ROOT, depth)])
        while len(visit_deque):
            curr_node, curr_depth = visit_deque.popleft()
            depths[curr_node] = curr_depth

            for next_node in graph[curr_node]:
                if not visit_list[next_node]:
                    visit_deque.append((next_node, curr_depth + 1))
                    visit_list[next_node] = True
                    parents[next_node][0] = curr_node

    def set_parent():
        bfs()
        for i in range(1, MAX_NUM_PARENTS):
            for j in range(1, n + 1):
                parents[j][i] = parents[parents[j][i-1]][i-1]

    set_parent()

    def lca(node_a: int, node_b: int) -> int:
        if depths[node_a] > depths[node_b]:
            node_a, node_b = node_b, node_a

        for i in range(MAX_NUM_PARENTS - 1, -1, -1):
            if depths[node_b] - depths[node_a] >= (1 << i):
                node_b = parents[node_b][i]

        if node_a == node_b:
            return node_a

        for i in range(MAX_NUM_PARENTS - 1, -1, -1):
            if parents[node_a][i] != parents[node_b][i]:
                node_a = parents[node_a][i]
                node_b = parents[node_b][i]
        return parents[node_a][0]

    m = int(input())
    for query in range(m):
        a, b = map(int, input().split())
        print(lca(a, b))


if __name__ == '__main__':
    main()
