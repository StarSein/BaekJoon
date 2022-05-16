import sys


sys.setrecursionlimit(int(1e4))


def input():
    return sys.stdin.readline().rstrip()


def main():
    def calc_dp(l: int, r: int):
        dp[l][r] = min(dp[l][k] + dp[k+1][r] for k in range(l, r)) + pref_sum[r+1] - pref_sum[l]


    t = int(input())
    for tc in range(t):
        k = int(input())
        sizes = list(map(int, input().split()))
        pref_sum = [0] * (k + 1)
        for idx, size in enumerate(sizes, start=1):
            pref_sum[idx] = pref_sum[idx-1] + size
        dp = [[-1 for col in range(k)] for row in range(k)]
        for i in range(k):
            dp[i][i] = 0

        for m in range(1, k):
            for i in range(k - m):
                calc_dp(i, i + m)

        print(dp[0][k-1])


if __name__ == '__main__':
    main()
