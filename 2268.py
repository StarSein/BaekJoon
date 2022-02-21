import sys
from typing import List


input = sys.stdin.readline


def main():
    n, m = map(int, input().split())
    fenwick_tree = [0] * (n + 1)

    for idx in range(m):
        opr = list(map(int, input().split()))
        if opr[0] == 0:
            i, j = opr[1], opr[2]
            print(query(i, j, fenwick_tree))
        elif opr[0] == 1:
            i, k = opr[1], opr[2]
            change = k - query(i, i, fenwick_tree)
            update(i, change, n, fenwick_tree)
        else:
            pass


def query(i: int, j: int, tree: List[int]) -> int:
    interval_sum = get_prefix_sum(j, tree) - get_prefix_sum(i - 1, tree)
    return interval_sum


def get_prefix_sum(x: int, tree: List[int]) -> int:
    prefix_sum = 0
    while x:
        prefix_sum += tree[x]
        x -= (x & -x)
    return prefix_sum


def update(i: int, change: int, n: int, tree: List[int]):
    while i <= n:
        tree[i] += change
        i += (i & -i)


if __name__ == '__main__':
    main()
