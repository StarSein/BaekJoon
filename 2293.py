import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, k = map(int, input().split())
    coin_vals = [int(input()) for i in range(n)]
    dp = [0] * (k + 1)
    dp[0] = 1
    for coin_val in coin_vals:
        for sum_val in range(1, k + 1):
            if sum_val >= coin_val:
                dp[sum_val] += dp[sum_val - coin_val]
    print(dp[k])


if __name__ == '__main__':
    main()
