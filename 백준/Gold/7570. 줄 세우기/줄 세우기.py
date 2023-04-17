from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    nums = list(map(int, input().split()))
    return N, nums


def solution(N: int, nums: List[int]) -> int:
    positions = [0 for _ in range(N + 1)]
    for pos, num in enumerate(nums, start=1):
        positions[num] = pos

    max_len = 1
    cur_len = 1
    for i in range(2, N + 1):
        if positions[i] > positions[i - 1]:
            cur_len += 1
        else:
            max_len = max(max_len, cur_len)
            cur_len = 1
    max_len = max(max_len, cur_len)

    return N - max_len


if __name__ == '__main__':
    print(solution(*read_input()))
