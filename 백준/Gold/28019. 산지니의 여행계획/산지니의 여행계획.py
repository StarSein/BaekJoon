from sys import stdin, setrecursionlimit
from typing import List, Tuple


setrecursionlimit(int(3e5))


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    start = int(input())
    return N, M, edges, start


def solution(N: int, M: int, edges: List[Tuple[int, int, int]], start: int) -> int:
    def find_parent(x: int) -> int:
        if parents[x] != x:
            parents[x] = find_parent(parents[x])
        return parents[x]

    def union(a: int, b: int):
        if ranks[a] < ranks[b]:
            a, b = b, a
        parents[b] = a
        ranks[a] = max(ranks[a], ranks[b] + 1)

    def dfs(cur: int, par: int) -> int:
        ret = 0
        for nex, cost in graph[cur]:
            if nex != par:
                ret = max(ret, cost + dfs(nex, cur))
        return ret

    parents = [i for i in range(N + 1)]
    ranks = [1 for i in range(N + 1)]
    graph = [[] for i in range(N + 1)]
    mst_len = 0
    for a, b, c in sorted(edges, key=lambda x: -x[2]):
        pa = find_parent(a)
        pb = find_parent(b)
        if pa != pb:
            union(pa, pb)
            mst_len += c
            graph[a].append((b, c))
            graph[b].append((a, c))

    max_cost = dfs(start, 0)
    return 2 * mst_len - max_cost


if __name__ == '__main__':
    print(solution(*read_input()))
