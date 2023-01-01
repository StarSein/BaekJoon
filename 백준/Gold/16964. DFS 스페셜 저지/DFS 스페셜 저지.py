from sys import stdin
from itertools import islice
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    edge_list = [tuple(map(int, input().split())) for _ in range(N - 1)]
    order = list(map(int, input().split()))
    return N, edge_list, order


def solution(N: int, edges: List[Tuple[int, int]], order: List[int]) -> int:
    ROOT = 1
    if order[0] != ROOT:
        return 0

    graph = [[] for _ in range(N + 1)]
    for a, b in edges:
        graph[a].append(b)
        graph[b].append(a)

    child_cnt = [0 for _ in range(N + 1)]
    parent = [0 for _ in range(N + 1)]

    def dfs(cur: int, par: int):
        parent[cur] = par
        for nex in graph[cur]:
            if nex != par:
                child_cnt[cur] += 1
                dfs(nex, cur)

    dfs(ROOT, 0)
    stack = [ROOT]
    for node in islice(order, 1, N):
        while child_cnt[stack[-1]] == 0:
            stack.pop()
        if parent[node] != stack[-1]:
            return 0
        child_cnt[stack[-1]] -= 1
        stack.append(node)
    return 1


if __name__ == '__main__':
    print(solution(*read_input()))
