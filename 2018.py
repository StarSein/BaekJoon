import sys


input = sys.stdin.readline


def solution(n: int) -> int:
    num_list = [num for num in range(n+1)]
    start, end = 1, 1
    continuous_sum = sum(num_list[start:end+1])
    cnt = 0
    while True:
        if continuous_sum == n:
            cnt += 1

        if end < n and continuous_sum <= n:
            end += 1
            continuous_sum += num_list[end]
        elif start < end and continuous_sum > n:
            continuous_sum -= num_list[start]
            start += 1
        else:
            return cnt


if __name__ == '__main__':
    n = int(input())
    sol = solution(n)
    print(sol)
