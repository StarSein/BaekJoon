from sys import stdin
from typing import List
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    K = int(input())
    nums = list(map(int, input().split()))
    return K, nums


def solution(K: int, nums: List[int]) -> int:
    heapq.heapify(nums)
    ans = 0
    while len(nums) >= 2:
        a = heapq.heappop(nums)
        b = heapq.heappop(nums)
        two_sum = a + b
        ans += two_sum
        heapq.heappush(nums, two_sum)
    return ans


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        print(solution(*read_input()))
