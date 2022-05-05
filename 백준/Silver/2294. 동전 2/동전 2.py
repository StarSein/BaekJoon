"""
dp[0] = 0
dp[k] = k원을 만들기 위해 최소한으로 필요한 동전의 개수
dp[k] = 1 + min(dp[k - val] for val in coin_vals if k >= val)
시간 복잡도: O(NK)
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, k = map(int, input().split())
    coin_vals = [int(input()) for _ in range(n)]
    INF = 10001
    dp = [INF] * (k + 1)
    dp[0] = 0
    for i in range(1, k + 1):
        min_nums = [dp[i - val] for val in coin_vals if i >= val]
        if min_nums:
            dp[i] = 1 + min(min_nums)
        else:
            dp[i] = INF
    print(dp[k] if dp[k] < INF else -1)


if __name__ == '__main__':
    main()
