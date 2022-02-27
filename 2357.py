import sys
from math import log2, ceil

INF = 1_000_000_000


def input():
    return sys.stdin.readline().rstrip()


def main():
    def make_min_seg_tree():
        t = size
        for idx in range(t):
            min_seg_tree[t + idx] = num_list[idx]
        while t >= 2:
            t >>= 1
            for idx in range(t, 2 * t):
                min_seg_tree[idx] = min(min_seg_tree[2*idx], min_seg_tree[2*idx+1])

    def min_query(l: int, r: int) -> int:
        l_idx = l + size - 1
        r_idx = r + size - 1

        res = INF
        while l_idx <= r_idx:
            if l_idx % 2 == 1:
                res = min(res, min_seg_tree[l_idx])
                l_idx += 1
            if r_idx % 2 == 0:
                res = min(res, min_seg_tree[r_idx])
                r_idx -= 1
            l_idx >>= 1
            r_idx >>= 1

        return res

    def make_max_seg_tree():
        t = size
        for idx in range(t):
            max_seg_tree[t + idx] = num_list[idx]
        while t >= 2:
            t >>= 1
            for idx in range(t, 2 * t):
                max_seg_tree[idx] = max(max_seg_tree[2*idx], max_seg_tree[2*idx+1])

    def max_query(l: int, r: int) -> int:
        l_idx = l + size - 1
        r_idx = r + size - 1

        res = 0
        while l_idx <= r_idx:
            if l_idx % 2 == 1:
                res = max(res, max_seg_tree[l_idx])
                l_idx += 1
            if r_idx % 2 == 0:
                res = max(res, max_seg_tree[r_idx])
                r_idx -= 1
            l_idx >>= 1
            r_idx >>= 1

        return res

    n, m = map(int, input().split())
    num_list = [int(input()) for num in range(n)]
    size = 1 << ceil(log2(n))
    num_list.extend([0] * (size - n))
    min_seg_tree = [0] * (2 * size)
    make_min_seg_tree()
    max_seg_tree = [0] * (2 * size)
    make_max_seg_tree()
    for inp in range(m):
        a, b = map(int, input().split())
        print(min_query(a, b), max_query(a, b))


main()
