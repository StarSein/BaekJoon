from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N = int(input())
    nums = list(map(int, input().split()))
    return N, nums


def solution(N: int, nums: List[int]) -> float:
    nums.sort()
    if N == 1:
        return nums[0]
    else:
        return max(float(nums[-2]), sum(nums) / N)


if __name__ == '__main__':
    print(solution(*read_test_case()))
