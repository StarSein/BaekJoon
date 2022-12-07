from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return (input() for _ in range(3))


def solution(s1: str, s2: str, s3: str) -> int:
    dp = [[[0 for k in range(len(s3) + 1)] for j in range(len(s2) + 1)] for i in range(len(s1) + 1)]
    for i, c1 in enumerate(s1, start=1):
        for j, c2 in enumerate(s2, start=1):
            for k, c3 in enumerate(s3, start=1):
                dp[i][j][k] = max(dp[i - 1][j][k], dp[i][j - 1][k], dp[i][j][k - 1])
                if c1 == c2 and c2 == c3:
                    dp[i][j][k] = max(dp[i][j][k], 1 + dp[i - 1][j - 1][k - 1])
    return dp[len(s1)][len(s2)][len(s3)]


if __name__ == '__main__':
    print(solution(*read_input()))

