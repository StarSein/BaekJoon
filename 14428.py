import sys
from math import log2, ceil


INF = int(1e9)


def input():
    return sys.stdin.readline().rstrip()


def main():
    def init_seg():
        k = size
        for i in range(k):
            seg_tree[k + i] = num_list[i]
        while k >= 2:
            k >>= 1
            for j in range(k, 2 * k):
                seg_tree[j] = min(seg_tree[2*j], seg_tree[2*j+1])

    def update_seg(i: int, v: int):
        idx = i + size - 1
        seg_tree[idx] = v

        while idx >= 2:
            idx >>= 1
            seg_tree[idx] = min(seg_tree[2*idx], seg_tree[2*idx+1])

    def query_seg(l: int, r: int) -> int:
        l_idx = l + size - 1
        r_idx = r + size - 1
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
    num_list = list(map(int, input().split()))
    pos_dict = dict()
    for pos, val in enumerate(num_list, start=1):
        if val not in pos_dict:
            pos_dict[val] = pos
    size = 1 << ceil(log2(n))
    num_list.extend([0] * (size - n))
    seg_tree = [0] * (2 * size)
    init_seg()
    m = int(input())
    for q in range(m):
        a, b, c = map(int, input().split())
        if a == 1:
            update_seg(b, c)
        else:
            min_val = query_seg(b, c)
            print(pos_dict[min_val])

main()
