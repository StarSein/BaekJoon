import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, k = map(int, input().split())
    coin_vals = [0] + [int(input()) for i in range(n)]
    coin_vals.sort()

    dp = [[0 for coin_val in range(n + 1)] for sum_val in range(k + 1)]
    dp[0][0] = 1
    for col in range(1, k + 1):
        for row in range(1, n + 1):
            dp[col][row] = sum(dp[col - coin_vals[row]][i] for i in range(row + 1))

    print(sum(dp[k]))


if __name__ == '__main__':
    main()
