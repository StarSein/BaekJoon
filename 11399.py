import sys
from typing import List

input = sys.stdin.readline


def solution(n: int, p_list: List[int]) -> int:
    p_list.sort()
    total_time = 0
    for idx, val in enumerate(p_list):
        total_time += (n - idx) * val
    return total_time


if __name__ == '__main__':
    n = int(input())
    p_list = list(map(int, input().split()))
    sol = solution(n, p_list)
    print(sol)