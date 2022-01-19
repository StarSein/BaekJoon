import sys


input = sys.stdin.readline
MAX_BUDGET = 100_000
MIN_BUDGET = 1


def solution() -> int:
    if sum(budget_list) <= total_budget:
        return max(budget_list)

    sol = binary_search(MIN_BUDGET, MAX_BUDGET)
    return sol


def binary_search(start: int, end: int) -> int:
    if start > end:
        return end

    mid = (start + end) // 2
    budget_sum = 0
    for idx, val in enumerate(budget_list):
        budget_sum += min(val, mid)

    if budget_sum > total_budget:
        return binary_search(start, mid-1)
    elif budget_sum < total_budget:
        return binary_search(mid+1, end)
    else:
        return mid


if __name__ == '__main__':
    n = int(input())
    budget_list = list(map(int, input().split()))
    total_budget = int(input())
    sol = solution()
    print(sol)
