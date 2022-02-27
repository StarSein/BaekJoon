import sys
from math import log2, ceil


DIV = 1_000_000_007


def input():
    return sys.stdin.readline().rstrip()


def main():
    def make_tree():
        t = size
        for idx in range(t):
            tree[t + idx] = num_list[idx]
        while t >= 2:
            t //= 2
            for idx in range(t, 2 * t):
                tree[idx] = tree[2*idx] * tree[2*idx+1] % DIV


    def update(x, v):
        node = x + size - 1
        tree[node] = v

        node //= 2
        while node >= 1:
            tree[node] = tree[2*node] * tree[2*node+1] % DIV
            node //= 2

    def query(l, r):
        l_idx = l + size - 1
        r_idx = r + size - 1
        res = 1
        while l_idx <= r_idx:
            if l_idx % 2 == 1:
                res *= tree[l_idx]
                l_idx += 1
            if r_idx % 2 == 0:
                res *= tree[r_idx]
                r_idx -= 1
            res %= DIV
            l_idx //= 2
            r_idx //= 2
        return res

    n, m, k = map(int, input().split())
    num_list = [int(input()) for num in range(n)]
    size = 1 << ceil(log2(n))
    num_list.extend([1] * (size - n))
    tree = [1] * (2 * size)
    make_tree()

    for command in range(m + k):
        a, b, c = map(int, input().split())
        if a == 1:
            update(b, c)
        else:
            print(query(b, c))


main()
