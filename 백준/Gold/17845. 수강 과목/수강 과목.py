import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, k = map(int, input().split())
    subjects = [tuple(map(int, input().split())) for _ in range(k)]
    dp = [0] * (n + 1)
    for value, cost in subjects:
        for time in range(n, max(0, cost - 1), -1):
            dp[time] = max(dp[time], dp[time-cost] + value)
    print(dp[n])


if __name__ == '__main__':
    main()
