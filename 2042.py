import sys
from typing import List


input = sys.stdin.readline


def main():
    n, m, k = map(int, input().split())
    num_list = list(int(input()) for num in range(n))

    prefix_sums = [0]
    for idx, num in enumerate(num_list):
        prefix_sums.append(prefix_sums[-1] + num_list[idx])

    bin_idx_tree = [0]
    for idx in range(1, n + 1):
        t = idx & -idx
        val = prefix_sums[idx] - prefix_sums[idx - t]
        bin_idx_tree.append(val)

    for query in range(m + k):
        a, b, c = map(int, input().split())
        if a == 1:
            change = c - get_interval_sum(b, b, bin_idx_tree)
            update_bi_tree(b, change, n, bin_idx_tree)
        elif a == 2:
            print(get_interval_sum(b, c, bin_idx_tree))
        else:
            pass


def update_bi_tree(b: int, change: int, n: int, bin_idx_tree: List[int]):
    idx = b
    while idx <= n:
        bin_idx_tree[idx] += change
        idx += (idx & -idx)


def get_interval_sum(b: int, c: int, bin_idx_tree: List[int]) -> int:
    prefix_sum_c = 0
    idx = c
    while idx:
        prefix_sum_c += bin_idx_tree[idx]
        idx -= (idx & -idx)

    prefix_sum_b = 0
    idx = b - 1
    while idx:
        prefix_sum_b += bin_idx_tree[idx]
        idx -= (idx & -idx)

    interval_sum = prefix_sum_c - prefix_sum_b
    return interval_sum


if __name__ == '__main__':
    main()
