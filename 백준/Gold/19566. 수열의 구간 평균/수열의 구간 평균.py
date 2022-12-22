"""
K = (pref[r] - pref[l]) / (r - l)
K(r - l) + pref[l] = pref[r]
pref[l] - Kl = pref[r] - Kr
"""
from sys import stdin
from collections import Counter
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, K = map(int, input().split())
    arr = list(map(int, input().split()))
    return N, K, arr


def solution(N: int, K: int, arr: List[int]) -> int:
    pref_sums = [0]
    for Ai in arr:
        pref_sums.append(pref_sums[-1] + Ai)

    counter = Counter()
    answer = 0
    for i, pref_sum in enumerate(pref_sums):
        key = pref_sum - K * i
        answer += counter[key]
        counter[key] += 1
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
