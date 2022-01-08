import sys

input = sys.stdin.readline


def solution(n: int, k: int, degree_list: list) -> int:
    start = 0
    end = start + k
    degree_sum = sum(degree_list[start:end])
    max_sum = degree_sum
    while start < n - k:
        degree_sum = degree_sum - degree_list[start] + degree_list[end]
        start += 1
        end += 1
        max_sum = max(max_sum, degree_sum)
    return max_sum


if __name__ == '__main__':
    n, k = map(int, input().split())
    degree_list = list(map(int, input().split()))
    sol = solution(n, k, degree_list)
    print(sol)