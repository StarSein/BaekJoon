from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    T = int(input())
    nums = [int(input()) for _ in range(T)]
    return T, nums


def get_divisors(n: int) -> List[int]:
    ret = []
    for i in range(1, n):
        if n % i == 0:
            ret.append(i)
    return ret


def solution(T: int, nums: List[int]) -> List[str]:
    SZ = 5001
    is_over = [False for _ in range(SZ)]
    for i in range(1, SZ):
        divisor = get_divisors(i)
        if sum(divisor) > i:
            is_over[i] = True

    answers = []
    for n in nums:
        divisors = get_divisors(n)
        condition = is_over[n] and all(not is_over[div] for div in divisors)
        answers.append("Good Bye" if condition else "BOJ 2022")
    return answers


if __name__ == '__main__':
    print(*solution(*read_input()), sep='\n')
