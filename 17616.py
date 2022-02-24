import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m, x = map(int, input().split())
    div_edges = [[] for node in range(n + 1)]
    rev_edges = [[] for node in range(n + 1)]
    for info in range(m):
        a, b = map(int, input().split())
        div_edges[b].append(a)
        rev_edges[a].append(b)
    u = bfs(x, div_edges)
    v = n + 1 - bfs(x, rev_edges)
    print(u, v)


def bfs(node, edges) -> int:
    queue = deque([node])
    is_visiting = [False] * (len(edges))
    rank = 0
    while len(queue):
        current_node = queue.pop()
        for superior_node in edges[current_node]:
            if is_visiting[superior_node]:
                continue

            queue.append(superior_node)
            is_visiting[superior_node] = True
        rank += 1
    return rank


if __name__ == '__main__':
    main()
