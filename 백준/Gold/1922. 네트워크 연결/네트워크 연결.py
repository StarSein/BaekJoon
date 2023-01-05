from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N = int(input())
    M = int(input())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    return N, M, edges


def solution(N: int, M: int, edges: List[Tuple[int, int, int]]) -> int:
    def find_root(node: int) -> int:
        if roots[node] != node:
            roots[node] = find_root(roots[node])
        return roots[node]

    def union(a: int, b: int) -> None:
        if a > b:
            a, b = b, a
        roots[b] = a

    roots = [node for node in range(N + 1)]
    edges.sort(key=lambda x: x[2])

    answer = 0
    for a, b, c in edges:
        ra = find_root(a)
        rb = find_root(b)
        if ra != rb:
            union(ra, rb)
            answer += c
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
