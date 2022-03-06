import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    W = 0
    V = 1
    n, k = map(int, input().split())
    stuff = [(0, 0)]
    for inp in range(n):
        stuff.append(tuple(map(int, input().split())))
    dp = [[0 for row in range(k + 1)] for col in range(n + 1)]
    for cur in range(1, n + 1):
        for weight_limit in range(1, k + 1):
            if stuff[cur][W] <= weight_limit:
                dp[cur][weight_limit] = max(dp[cur-1][weight_limit], dp[cur-1][weight_limit - stuff[cur][W]] + stuff[cur][V])
            else:
                dp[cur][weight_limit] = dp[cur-1][weight_limit]
    print(dp[n][k])


if __name__ == '__main__':
    main()
