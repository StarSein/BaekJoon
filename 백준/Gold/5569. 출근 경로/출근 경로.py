from sys import stdin


input = lambda: stdin.readline().rstrip()


def solution(W: int, H: int) -> int:
    MOD = int(1e5)

    US = 0  # Up Straight
    RS = 1  # Right straight
    UT = 2  # Up Turn
    RT = 3  # Right Turn

    dp = [[[0] * 4 for col in range(W)] for row in range(H)]
    for r in range(H):
        dp[r][0][US] = 1
    for c in range(W):
        dp[0][c][RS] = 1

    for r in range(1, H):
        for c in range(1, W):
            dp[r][c][US] = (dp[r - 1][c][US] + dp[r - 1][c][UT]) % MOD
            dp[r][c][RS] = (dp[r][c - 1][RS] + dp[r][c - 1][RT]) % MOD
            dp[r][c][UT] = dp[r - 1][c][RS]
            dp[r][c][RT] = dp[r][c - 1][US]

    return sum(dp[H - 1][W - 1]) % MOD


if __name__ == '__main__':
    w, h = map(int, input().split())
    print(solution(w, h))
