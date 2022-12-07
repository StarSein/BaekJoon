from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return int(input())


def solution(n: int) -> int:
    SZ = int(1e7)
    dp = [0 for _ in range(SZ)]
    dp[1], dp[2], dp[3] = 1, 2, 2
    j = 3
    cnt = dp[3]
    for i in range(4, SZ):
        if cnt:
            cnt -= 1
        else:
            j += 1
            cnt = dp[j] - 1
        dp[i] = j

    cnt = 0
    for i in range(1, SZ):
        cnt += dp[i]
        if cnt >= n:
            return i
    return -1


if __name__ == '__main__':
    print(solution(read_input()))
