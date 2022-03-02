import sys
from math import log2, ceil


def input():
    return sys.stdin.readline().rstrip()


def main():
    INF = int(1e9)

    def init_seg_tree():
        for i in range(n):
            seg_tree[tree_size + i] = a_list[i]

        t = tree_size >> 1
        while t >= 1:
            for idx in range(t, 2 * t):
                seg_tree[idx] = min(seg_tree[2*idx], seg_tree[2*idx+1])
            t >>= 1

    def update(x: int, v: int):
        x_idx = x + tree_size - 1
        seg_tree[x_idx] = v
        t = x_idx >> 1
        while t >= 1:
            seg_tree[t] = min(seg_tree[2*t], seg_tree[2*t+1])
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

    n = int(input())
    a_list = list(map(int, input().split()))

    tree_size = 2 ** ceil(log2(n))
    seg_tree = [INF] * (2 * tree_size)
    init_seg_tree()
    m = int(input())
    for q in range(m):
        type, a, b = map(int, input().split())
        if type == 1:
            update(a, b)
        else:
            print(query(a, b))


if __name__ == '__main__':
    main()
