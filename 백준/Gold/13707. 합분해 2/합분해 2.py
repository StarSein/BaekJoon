from sys import stdin


def input():
    return stdin.readline().rstrip()


def solution(n: int, k: int) -> int:
    MOD = int(1e9)

    dp = [1 for _ in range(n + 1)]
    for _ in range(k - 1):
        for i in range(1, n + 1):
            dp[i] = (dp[i] + dp[i - 1]) % MOD
    return dp[n] % MOD


if __name__ == '__main__':
    N, K = map(int, input().split())
    print(solution(N, K))
    