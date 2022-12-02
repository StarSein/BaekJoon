from sys import stdin, setrecursionlimit
from typing import List, Tuple


setrecursionlimit(int(5e5))


def input():
    return stdin.readline().rstrip()


def read_input():
    N, Q = map(int, input().split())
    parents = [0, 0] + [int(input()) for _ in range(N - 1)]
    colors = [0] + [int(input()) for _ in range(N)]
    queries = [tuple(map(int, input().split())) for _ in range(N - 1 + Q)]
    return N, Q, parents, colors, queries


def solution(N: int, Q: int, parents: List[int], colors: List[int], queries: List[Tuple[int, int]]) -> List[int]:
    roots = [i for i in range(N + 1)]
    color_sets = [{colors[i]} for i in range(N + 1)]

    def find_root(x: int) -> int:
        if roots[x] != x:
            roots[x] = find_root(roots[x])
        return roots[x]

    def union(a: int, b: int):
        if len(color_sets[a]) < len(color_sets[b]):
            a, b = b, a
        color_sets[a] |= color_sets[b]
        roots[b] = a

    answers = []
    for i in range(len(queries) - 1, -1, -1):
        t, a = queries[i]
        if t == 1:
            ra = find_root(a)
            rb = find_root(parents[a])
            union(ra, rb)
        else:
            ra = find_root(a)
            answers.append(len(color_sets[ra]))
    answers.reverse()
    return answers


if __name__ == '__main__':
    print(*solution(*read_input()), sep='\n')
