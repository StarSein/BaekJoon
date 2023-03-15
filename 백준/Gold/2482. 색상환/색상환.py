from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    K = int(input())
    return N, K


def solution(N: int, K: int) -> int:
    MOD = int(1e9 + 3)

    dp = [[[0 for k in range(K + 1)] for j in range(N + 1)] for i in range(2)]
    dp[0][0][0] = 1
    dp[0][1][0] = 1
    dp[1][1][1] = 1
    for i in range(2):
        for j in range(2, N + 1):
            dp[i][j][0] = dp[i][j - 1][0]
            for k in range(1, K + 1):
                dp[i][j][k] = (dp[i][j - 1][k] + dp[i][j - 2][k - 1]) % MOD
    return (dp[0][N][K] + dp[1][N - 1][K]) % MOD


if __name__ == '__main__':
    print(solution(*read_input()))
