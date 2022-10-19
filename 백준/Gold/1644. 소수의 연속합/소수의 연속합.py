"""
N = 4000'000 일 때 소수의 개수 283146
"""

from sys import stdin
from typing import List

input = lambda: stdin.readline().rstrip()


def get_primes(n: int) -> List[int]:
    prime = [True] * (n + 1)
    for i in range(2, n // 2 + 1):
        if prime[i]:
            for j in range(2 * i, n + 1, i):
                prime[j] = False
    return [i for i in range(2, n + 1) if prime[i]]


def solution(n: int) -> int:
    pref_sums = [0] + get_primes(n)
    for i in range(1, len(pref_sums)):
        pref_sums[i] += pref_sums[i - 1]

    lp = len(pref_sums) - 2
    rp = len(pref_sums) - 1
    case_cnt = 0
    while rp:
        interval_sum = pref_sums[rp] - pref_sums[lp]
        if lp > 0 and interval_sum < n:
            lp -= 1
        else:
            rp -= 1

        if interval_sum == n:
            case_cnt += 1

    return case_cnt


if __name__ == '__main__':
    N = int(input())
    print(solution(N))
