"""
가장 긴 증가하는 부분수열(LIS)을 기준으로 삼고, 나머지 수들을 움직이면 된다.
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    nums = [0] + [int(input()) for _ in range(n)]
    dp = [0] * (n + 1)
    for i in range(1, n + 1):
        dp[i] = max(dp[k] for k in range(0, i) if nums[i] > nums[k]) + 1
    print(n - max(dp))


if __name__ == '__main__':
    main()
