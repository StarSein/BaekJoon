from sys import stdin, setrecursionlimit
from typing import Tuple, List

setrecursionlimit(int(1e6))


def input():
    return stdin.readline().rstrip()


def read_input() -> Tuple[int, int, List[Tuple[int, int]]]:
    N, L = map(int, input().split())
    drinks = [tuple(map(int, input().split())) for _ in range(N)]
    return N, L, drinks


def solution(N: int, L: int, drinks: List[Tuple[int, int]]):
    empty = [True for _ in range(L + 1)]
    roots = [i for i in range(L + 1)]

    def find_root(x: int):
        if roots[x] != x:
            roots[x] = find_root(roots[x])
        return roots[x]

    def union(a: int, b: int):
        roots[a] = b

    for ai, bi in drinks:
        if empty[ai]:
            empty[ai] = False
            ra = find_root(ai)
            rb = find_root(bi)
            union(ra, rb)
        elif empty[bi]:
            empty[bi] = False
            ra = find_root(ai)
            rb = find_root(bi)
            union(rb, ra)
        elif empty[(ra := find_root(ai))]:
            empty[ra] = False
            rb = find_root(bi)
            union(ra, rb)
        elif empty[(rb := find_root(bi))]:
            empty[rb] = False
        else:
            print("SMECE")
            continue
        print("LADICA")


if __name__ == '__main__':
    solution(*read_input())
