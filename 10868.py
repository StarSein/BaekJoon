import sys
from math import log2, ceil


def input():
    return sys.stdin.readline().rstrip()


def main():
    INF = int(1e9)

    def init_seg_tree():
        for i in range(n):
            seg_tree[tree_size + i] = num_list[i]

        t = tree_size >> 1
        while t >= 1:
            for i in range(t, 2 * t):
                seg_tree[i] = min(seg_tree[2*i], seg_tree[2*i+1])
            t >>= 1

    def query(l: int, r: int) -> int:
        l_idx = l + tree_size - 1
        r_idx = r + tree_size - 1

        res = INF
        while l_idx <= r_idx:
            if l_idx % 2 == 1:
                res = min(res, seg_tree[l_idx])
                l_idx += 1
            if r_idx % 2 == 0:
                res = min(res, seg_tree[r_idx])
                r_idx -= 1
            l_idx >>= 1
            r_idx >>= 1
        return res

    n, m = map(int, input().split())
    num_list = [int(input()) for num in range(n)]
    tree_size = 2 ** ceil(log2(n))
    seg_tree = [INF] * (2 * tree_size)
    init_seg_tree()
    for q in range(m):
        a, b = map(int, input().split())
        print(query(a, b))


if __name__ == '__main__':
    main()

