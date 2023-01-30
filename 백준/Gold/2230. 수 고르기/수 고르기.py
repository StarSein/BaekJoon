"""
O(NlogN)으로 최솟값을 찾으려면
정렬 후 투 포인터를 사용해야겠다.
"""

from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N, M = map(int, input().split())
    A = [int(input()) for _ in range(N)]
    return N, M, A


def solution(N: int, M: int, A: List[int]) -> int:
    A.sort()
    l = 0
    r = 0
    best_diff = A[-1] - A[0]
    while l < N:
        cur_diff = A[r] - A[l]
        is_over = False
        if cur_diff >= M:
            best_diff = min(best_diff, cur_diff)
            is_over = True
        if (is_over and l < N - 1) or r == N - 1:
            l += 1
        else:
            r += 1
    return best_diff


if __name__ == '__main__':
    print(solution(*read_test_case()))
