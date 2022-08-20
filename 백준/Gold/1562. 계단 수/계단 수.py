import sys


def input():
    return sys.stdin.readline().rstrip()


def getNumStair(length: int, mask: int, digit: int) -> int:
    if length == 0:
        return 0

    if not mask & 1 << digit:
        return 0

    if dp[digit][length][mask] != -1:
        return dp[digit][length][mask]

    ret = 0
    if digit == 0:
        ret += getNumStair(length - 1, mask, 1) + getNumStair(length - 1, mask ^ 1 << digit, 1)
    elif digit == 9:
        ret += getNumStair(length - 1, mask, 8) + getNumStair(length - 1, mask ^ 1 << digit, 8)
    else:
        ret += getNumStair(length - 1, mask, digit + 1) \
               + getNumStair(length - 1, mask, digit - 1) \
               + getNumStair(length - 1, mask ^ 1 << digit, digit + 1) \
               + getNumStair(length - 1, mask ^ 1 << digit, digit - 1)
    dp[digit][length][mask] = ret
    return ret


def solution() -> int:
    return sum([getNumStair(N, (1 << NUM_DIGIT) - 1, i) for i in range(NUM_DIGIT)])


if __name__ == '__main__':
    N = int(input())

    MOD = int(1e9)

    NUM_DIGIT = 10

    dp = [[[-1 for k in range(1 << NUM_DIGIT)] for j in range(N + 1)] for i in range(NUM_DIGIT)]
    dp[0][1][1 << 0] = 0
    for i in range(1, NUM_DIGIT):
        dp[i][1][1 << i] = 1

    print(solution() % MOD)
