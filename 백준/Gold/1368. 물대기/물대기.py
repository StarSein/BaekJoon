from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    W = [int(input()) for _ in range(N)]
    matrix = [list(map(int, input().split())) for _ in range(N)]
    return N, W, matrix


def solution(N: int, W: List[int], matrix: List[List[int]]) -> int:
    def find_parent(x: int) -> int:
        if parents[x] != x:
            parents[x] = find_parent(parents[x])
        return parents[x]

    def union(a: int, b: int):
        if ranks[a] < ranks[b]:
            a, b = b, a
        parents[b] = a
        ranks[a] = max(ranks[a], ranks[b] + 1)

    edges = []
    for i, w in enumerate(W, start=1):
        edges.append((0, i, w))
    for a in range(1, N):
        for b in range(a + 1, N + 1):
            edges.append((a, b, matrix[a - 1][b - 1]))

    parents = [i for i in range(N + 1)]
    ranks = [1 for i in range(N + 1)]
    answer = 0
    for a, b, w in sorted(edges, key=lambda x: x[2]):
        pa = find_parent(a)
        pb = find_parent(b)
        if pa != pb:
            union(pa, pb)
            answer += w
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
