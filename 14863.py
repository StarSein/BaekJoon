import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    INF = -int(1e9)
    TIME = 0
    FUND = 1
    n, k = map(int, input().split())
    walk = [0]
    bike = [0]
    for inp in range(n):
        walk_time, walk_fund, bike_time, bike_fund = map(int, input().split())
        walk.append((walk_time, walk_fund))
        bike.append((bike_time, bike_fund))
    dp = [[0 for row in range(k + 1)] for col in range(n + 1)]
    for cur in range(1, n + 1):
        for time_limit in range(0, k + 1):
            dp[cur][time_limit] = INF
            if walk[cur][TIME] <= time_limit:
                dp[cur][time_limit] = max(dp[cur][time_limit], dp[cur-1][time_limit - walk[cur][TIME]] + walk[cur][FUND])
            if bike[cur][TIME] <= time_limit:
                dp[cur][time_limit] = max(dp[cur][time_limit], dp[cur-1][time_limit - bike[cur][TIME]] + bike[cur][FUND])
    print(dp[n][k])


if __name__ == '__main__':
    main()
