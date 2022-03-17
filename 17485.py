import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    matrix = [list(map(int, input().split())) for col in range(n)]
    INF = 100001
    dp = [[[INF, INF, INF] for row in range(m)] for col in range(n)]
    LEFT = 0
    DOWN = 1
    RIGHT = 2
    dp[0] = [[matrix[0][row]] * 3 for row in range(m)]
    for col in range(1, n):
        row = 0
        dp[col][row][DOWN] = dp[col-1][row][LEFT] + matrix[col][row]
        dp[col][row][LEFT] = min(dp[col-1][row+1][DOWN], dp[col-1][row+1][RIGHT]) + matrix[col][row]

        for row in range(1, m - 1):
            dp[col][row][LEFT] = min(dp[col-1][row+1][DOWN], dp[col-1][row+1][RIGHT]) + matrix[col][row]
            dp[col][row][RIGHT] = min(dp[col-1][row-1][DOWN], dp[col-1][row-1][LEFT]) + matrix[col][row]
            dp[col][row][DOWN] = min(dp[col-1][row][LEFT], dp[col-1][row][RIGHT]) + matrix[col][row]

        row = m - 1
        dp[col][row][DOWN] = dp[col-1][row][RIGHT] + matrix[col][row]
        dp[col][row][RIGHT] = min(dp[col-1][row-1][DOWN], dp[col-1][row-1][LEFT]) + matrix[col][row]

    min_cost = min(min(dp[n-1][row]) for row in range(m))
    print(min_cost)


if __name__ == '__main__':
    main()
