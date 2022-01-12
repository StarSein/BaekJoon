import sys
from typing import List


input = sys.stdin.readline
MAX_X = 1_000_000


def solution(k: int, ice_amounts: List[int]) -> int:
    if k >= (MAX_X / 2):
        return sum(ice_amounts)

    current_sum = sum(ice_amounts[:2*k+1])
    max_sum = current_sum
    subtract_idx = 0
    add_idx = 2 * k + 1
    while add_idx <= MAX_X:
        current_sum = current_sum - ice_amounts[subtract_idx] + ice_amounts[add_idx]
        max_sum = max(max_sum, current_sum)
        subtract_idx += 1
        add_idx += 1
    return max_sum


if __name__ == '__main__':
    n, k = map(int, input().split())
    ice_amounts = [0] * (MAX_X + 1)
    for i in range(n):
        g, x = map(int, input().split())
        ice_amounts[x] = g
    sol = solution(k, ice_amounts)
    print(sol)
