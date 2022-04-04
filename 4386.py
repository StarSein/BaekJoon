"""
최소 신장 트리를 이용한 풀이
최대 100개의 노드, 최대 4950개의 간선
크루스칼 알고리즘으로 풀어보자.
"""
import sys
from math import sqrt
from typing import Tuple


def input():
    return sys.stdin.readline().rstrip()


def main():
    def get_dist(a: Tuple[float, float], b: Tuple[float, float]) -> float:
        return sqrt((a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2)

    n = int(input())
    pos_list = [tuple(map(float, input().split())) for star in range(n)]
    edge_list = []
    for depart in range(n):
        for arrive in range(depart + 1, n):
            edge_list.append((get_dist(pos_list[depart], pos_list[arrive]), depart, arrive))
    edge_list.sort()

    def find_root(x: int) -> int:
        if roots[x] == x:
            return x

        roots[x] = find_root(roots[x])
        return roots[x]

    def union(root_a: int, root_b: int):
        if root_a > root_b:
            root_a, root_b = root_b, root_a

        roots[root_b] = root_a

    roots = [star for star in range(n)]
    i = 0
    total_dist = 0
    while i < len(edge_list):
        dist, node_a, node_b = edge_list[i]

        root_a = find_root(node_a)
        root_b = find_root(node_b)
        if root_a != root_b:
            total_dist += dist
            union(root_a, root_b)
        i += 1

    print(round(total_dist, 2))


if __name__ == '__main__':
    main()
