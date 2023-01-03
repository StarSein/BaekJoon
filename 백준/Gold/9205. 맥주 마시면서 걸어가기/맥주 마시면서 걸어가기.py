"""
맥주는 항상 20병을 유지하는 것이 최적이고,
그렇게 하는 데에 아무런 제약이 없으므로
길이가 1000m 이하인 간선만 이용할 수 있다는 조건만 적용하면 된다.
"""

from sys import stdin
from collections import deque
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_test_case():
    n = int(input())
    home = tuple(map(int, input().split()))
    stores = [tuple(map(int, input().split())) for _ in range(n)]
    festival = tuple(map(int, input().split()))
    return n, stores, home, festival


def solution(n: int, stores: List[Tuple[int, int]], home: Tuple[int, int], festival: Tuple[int, int]) -> str:
    DIST_LIMIT = 1_000
    visited = [False for _ in range(n)]
    dq = deque()
    dq.append(home)
    while dq:
        cx, cy = dq.popleft()
        if abs(cx - festival[0]) + abs(cy - festival[1]) <= DIST_LIMIT:
            return "happy"
        for i, (nx, ny) in enumerate(stores):
            if abs(nx - cx) + abs(ny - cy) <= DIST_LIMIT and not visited[i]:
                dq.append((nx, ny))
                visited[i] = True
    return "sad"


def main():
    t = int(input())
    for _ in range(t):
        print(solution(*read_test_case()))


if __name__ == '__main__':
    main()
