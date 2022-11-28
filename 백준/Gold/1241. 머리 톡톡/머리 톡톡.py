"""
1. 각 번호의 개수를 센다
2. 각 번호의 약수들의 개수의 총합을 출력하면 된다
시간 복잡도 O(N * sqrtN)
"""
from sys import stdin
from math import sqrt
from typing import Tuple, List


def input():
    return stdin.readline().rstrip()


def read_input() -> Tuple[int, List[int]]:
    N = int(input())
    nums = [int(input()) for _ in range(N)]
    return N, nums


def get_divisors(num: int) -> List[int]:
    ret = []
    rn = int(sqrt(num))
    for i in range(1, rn):
        if num % i == 0:
            ret.append(i)
            ret.append(num // i)
    if num % rn == 0:
        ret.append(rn)
        if num // rn != rn:
            ret.append(num // rn)
    return ret


def solution(N: int, nums: List[int]) -> List[int]:
    MAX_NUM = int(1e6)
    cnts = [0 for _ in range(MAX_NUM + 1)]
    for num in nums:
        cnts[num] += 1

    answers = []
    for num in nums:
        divisors = get_divisors(num)
        ans = sum(cnts[d] for d in divisors) - 1
        answers.append(ans)
    return answers


if __name__ == '__main__':
    print(*solution(*read_input()), sep='\n')
