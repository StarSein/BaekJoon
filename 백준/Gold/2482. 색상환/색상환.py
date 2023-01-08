from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N = int(input())
    K = int(input())
    return N, K


def solution(N: int, K: int) -> int:
    MOD = int(1e9 + 3)
    dp = [[[0, 0] for j in range(K + 1)] for i in range(N + 1)]
    for i in range(N + 1):
        dp[i][0][0] = 1
    for i in range(1, N + 1):
        for j in range(1, K + 1):
            dp[i][j][0] = (dp[i - 1][j][1] + dp[i - 1][j][0]) % MOD
            dp[i][j][1] = dp[i - 1][j - 1][0]
    answer = (dp[N - 1][K][1] + dp[N][K][0]) % MOD
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
