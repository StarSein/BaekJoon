from sys import stdin
from typing import Tuple


def input():
    return stdin.readline().rstrip()


def solution(n: int, k: int, intervals: Tuple[Tuple[int, int], ...], route: Tuple[int, ...]) -> int:
    pref_sums = [0 for _ in range(n + 1)]
    prev_e = 0
    for i, (s, e) in enumerate(intervals, start=1):
        if s <= prev_e:
            pref_sums[i] = pref_sums[i - 1]
        else:
            pref_sums[i] = pref_sums[i - 1] + s - prev_e
        prev_e = max(prev_e, e)

    ans = pref_sums[route[0]] - pref_sums[1]
    for i in range(1, k):
        a = route[i - 1]
        b = route[i]
        if a > b:
            a, b = b, a
        ans += pref_sums[b] - pref_sums[a]
    return ans


if __name__ == '__main__':
    N, K = map(int, input().split())
    interval_tuple = tuple(tuple(map(int, input().split())) for _ in range(N))
    visit_tuple = tuple(map(int, input().split()))
    print(solution(N, K, interval_tuple, visit_tuple))
