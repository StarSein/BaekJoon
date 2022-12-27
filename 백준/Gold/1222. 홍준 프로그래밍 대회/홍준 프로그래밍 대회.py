from sys import stdin
from math import isqrt
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    nums = list(map(int, input().split()))
    return N, nums


def solution(N: int, nums: List[int]) -> int:
    def get_divisors(x: int) -> List[int]:
        divisors = []
        for i in range(1, isqrt(x) + 1):
            if x % i == 0:
                divisors.append(i)
                divisors.append(x // i)
        if isqrt(x) ** 2 == x:
            divisors.pop()
        return divisors

    MAX_NUM = int(2e6)
    counts = [0 for _ in range(MAX_NUM + 1)]
    for num in nums:
        for divisor in get_divisors(num):
            counts[divisor] += 1
    answer = -1
    for team_size in range(1, MAX_NUM + 1):
        if counts[team_size] < 2:
            continue
        answer = max(answer, counts[team_size] * team_size)
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
