import sys
from math import log2, ceil


INF = int(1e9)


def input():
    return sys.stdin.readline().rstrip()


def main():
    def init_seg():
        k = size
        for i in range(k):
            seg_tree[k + i] = i
        while k >= 2:
            k >>= 1
            for j in range(k, 2 * k):
                if num_list[seg_tree[2*j]] <= num_list[seg_tree[2*j+1]]:
                    seg_tree[j] = seg_tree[2*j]
                else:
                    seg_tree[j] = seg_tree[2*j+1]

    def update_seg(i: int, v: int):
        num_list[i-1] = v
        idx = i + size - 1

        while idx >= 2:
            idx >>= 1
            if num_list[seg_tree[2*idx]] <= num_list[seg_tree[2*idx+1]]:
                seg_tree[idx] = seg_tree[2*idx]
            else:
                seg_tree[idx] = seg_tree[2*idx+1]

    def query_seg(l: int, r: int) -> int:
        l_idx = l + size - 1
        r_idx = r + size - 1
        curr_min = INF
        res = INF
        while l_idx <= r_idx:
            if l_idx % 2 == 1:
                if num_list[seg_tree[l_idx]] < curr_min:
                    curr_min = num_list[seg_tree[l_idx]]
                    res = seg_tree[l_idx]
                elif num_list[seg_tree[l_idx]] == curr_min:
                    res = min(res, seg_tree[l_idx])
                l_idx += 1
            if r_idx % 2 == 0:
                if num_list[seg_tree[r_idx]] < curr_min:
                    curr_min = num_list[seg_tree[r_idx]]
                    res = seg_tree[r_idx]
                elif num_list[seg_tree[r_idx]] == curr_min:
                    res = min(res, seg_tree[r_idx])
                r_idx -= 1
            l_idx >>= 1
            r_idx >>= 1
        return res

    n = int(input())
    num_list = list(map(int, input().split()))

    size = 1 << ceil(log2(n))
    num_list.extend([INF] * (size - n))
    seg_tree = [0] * (2 * size)
    init_seg()
    m = int(input())
    for q in range(m):
        a, b, c = map(int, input().split())
        if a == 1:
            update_seg(b, c)
        else:
            min_pos = query_seg(b, c) + 1
            print(min_pos)


main()
