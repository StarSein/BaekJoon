from sys import stdin
from typing import List
import heapq


def input():
    return stdin.readline().rstrip()


def solution(n: int, arr: List[int]) -> int:
    ps = [0 for _ in range(n + 1)]
    for i in range(1, n + 1):
        ps[i] = ps[i - 1] + arr[i - 1]

    INF = int(2e9)
    dp = [INF for _ in range(n + 1)]

    heap = [(0, 0)]
    for i in range(1, n + 1):
        j = 0
        while heap and heap[0][0] <= ps[i]:
            j = max(j, heapq.heappop(heap)[1])
        dp[i] = ps[i] - ps[j]
        heapq.heappush(heap, (dp[j] + ps[j], j))
        heapq.heappush(heap, (dp[i] + ps[i], i))

    ans = min(max(dp[i], ps[n] - ps[i]) for i in range(1, n + 1))
    return ans


if __name__ == '__main__':
    N = int(input())
    A = list(map(int, input().split()))
    print(solution(N, A))
