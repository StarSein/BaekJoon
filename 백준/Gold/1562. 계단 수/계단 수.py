import sys


def input():
    return sys.stdin.readline().rstrip()


def solution(N: int) -> int:
    MOD = int(1e9)
    NUM_DIGIT = 10
    dp = [[[0 for i in range(1 << NUM_DIGIT)] for j in range(N + 1)] for k in range(NUM_DIGIT)]
    for digit in range(1, NUM_DIGIT):
        dp[digit][1][1 << digit] = 1

    for length in range(1, N):
        for digit in range(NUM_DIGIT):
            for mask in range(1 << NUM_DIGIT):
                val = dp[digit][length][mask]
                if 1 <= digit <= 8:
                    dp[digit + 1][length + 1][mask | 1 << digit + 1] += val
                    dp[digit - 1][length + 1][mask | 1 << digit - 1] += val
                elif digit == 0:
                    dp[1][length + 1][mask | 1 << 1] += val
                else:
                    dp[8][length + 1][mask | 1 << 8] += val
    return sum([dp[digit][N][(1 << NUM_DIGIT) - 1] for digit in range(NUM_DIGIT)]) % MOD


if __name__ == '__main__':
    print(solution(int(input())))
