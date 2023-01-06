from sys import stdin
from collections import deque
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N, K = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(K)]
    return N, K, edges


def solution(N: int, K: int, edges: List[Tuple[int, int, int]]) -> Tuple[int, int]:
    def find_root(node: int) -> int:
        if roots[node] != node:
            roots[node] = find_root(roots[node])
        return roots[node]

    def union(a: int, b: int):
        if a > b:
            a, b = b, a
        roots[b] = a

    edges.sort(key=lambda x: x[2])
    roots = [node for node in range(N)]
    MST_edges = []
    answer1 = 0
    for a, b, c in edges:
        ra = find_root(a)
        rb = find_root(b)
        if ra != rb:
            union(ra, rb)
            MST_edges.append((a, b, c))
            answer1 += c

    graph = [[] for _ in range(N)]
    for a, b, c in MST_edges:
        graph[a].append((b, c))
        graph[b].append((a, c))

    def dfs(cur: int, par: int) -> Tuple[int, int]:
        cur_node, cur_dist = cur, 0
        for nex, weight in graph[cur]:
            if nex != par:
                nex_node, nex_dist = dfs(nex, cur)
                if weight + nex_dist > cur_dist:
                    cur_dist = weight + nex_dist
                    cur_node = nex_node
        return cur_node, cur_dist

    x, _ = dfs(0, -1)
    y, answer2 = dfs(x, -1)
    return answer1, answer2


if __name__ == '__main__':
    print(*solution(*read_test_case()), sep='\n')



