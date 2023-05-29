from sys import stdin
from typing import List

def input():
    return stdin.readline().rstrip()


def read_input():
    p = input()
    s = [input() for _ in range(2)]
    return p, s

def solution(p: str, s: List[str]) -> int:
    J, K = len(s[0]), len(p)
    dp = [[[0 for k in range(K + 1)] for j in range(J + 1)] for i in range(2)]
    for i in range(2):
        for j in range(J + 1):
            dp[i][j][0] = 1

    for j in range(J):
        for k in range(K):
            for i in range(2):
                if s[i][j] == p[k]:
                    dp[i][j + 1][k + 1] = dp[i][j][k + 1] + dp[1 - i][j][k]
                else:
                    dp[i][j + 1][k + 1] = dp[i][j][k + 1]

    answer = dp[0][J][K] + dp[1][J][K]
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
