import sys


input = sys.stdin.readline


def solution(n: int) -> int:
    start, end = 1, 1
    continuous_sum = end
    cnt = 0
    while True:
        if continuous_sum == n:
            cnt += 1

        if end < n and continuous_sum <= n:
            end += 1
            continuous_sum += end
        elif start < end and continuous_sum > n:
            continuous_sum -= start
            start += 1
        else:
            return cnt


if __name__ == '__main__':
    n = int(input())
    sol = solution(n)
    print(sol)
