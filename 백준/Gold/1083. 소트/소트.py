from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    nums = list(map(int, input().split()))
    S = int(input())
    return N, nums, S


def solution(N: int, nums: List[int], S: int) -> List[int]:
    for i in range(N):
        max_num = nums[i]
        max_pos = i
        for j in range(1, min(N - i, S + 1)):
            if nums[i + j] > max_num:
                max_num = nums[i + j]
                max_pos = i + j
        if max_pos == i:
            continue
        for j in range(max_pos, i, -1):
            nums[j - 1], nums[j] = nums[j], nums[j - 1]
        S -= (max_pos - i)
    return nums


if __name__ == '__main__':
    print(*solution(*read_input()))
