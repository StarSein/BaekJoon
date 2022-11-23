from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def solution(n: int, p: int, q: int,
             x: List[str], p1: List[str], p2: List[str]) -> int:
    x.append("E")
    p1.append("N")
    p2.append("N")
    dp = [[[-1 for k in range(q + 1)] for j in range(p + 1)] for i in range(n + 1)]
    dp[0][0][0] = 0
    for i in range(n + 1):
        for j in range(p + 1):
            for k in range(q + 1):
                val = dp[i][j][k]
                if i:
                    val = max(val, dp[i - 1][j][k])
                if j:
                    val = max(val, dp[i][j - 1][k])
                if k:
                    val = max(val, dp[i][j][k - 1])
                if val != -1:
                    if p1[j] == x[i]:
                        dp[i + 1][j + 1][k] = max(dp[i + 1][j + 1][k], val + 1)
                    if p2[k] == x[i]:
                        dp[i + 1][j][k + 1] = max(dp[i + 1][j][k + 1], val + 1)
                dp[i][j][k] = val
    return dp[n][p][q]


if __name__ == '__main__':
    N, P, Q = map(int, input().split())
    X = input().split()
    P1 = input().split()
    P2 = input().split()
    print(solution(N, P, Q, X, P1, P2))
