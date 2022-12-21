from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return int(input())


def solution(M: int) -> int:
    dp = [1]
    while True:
        num = 5 * dp[-1] + 1
        if num > M:
            break
        dp.append(num)
    m = M
    answer = 0
    idx = len(dp) - 1
    cnt = 0
    while m:
        if m >= dp[idx]:
            m -= dp[idx]
            answer += 5 ** (idx + 1)
            cnt += 1
            if cnt == 5:
                return -1
        else:
            cnt = 0
            idx -= 1
    return answer


if __name__ == '__main__':
    print(solution(read_input()))
