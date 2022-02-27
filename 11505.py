import sys
from math import log2, ceil


DIV = 1_000_000_007


def input():
    return sys.stdin.readline().rstrip()


def main():
    def make_tree(node, s, e):
        if s == e:
            tree[node] = num_list[s-1]
            return

        mid = s + (e - s) // 2
        make_tree(2*node, s, mid)
        make_tree(2*node+1, mid+1, e)

        tree[node] = tree[2*node] * tree[2*node+1]

    def update(x, v, node, s, e):
        if s == e:
            tree[node] = v
            return

        mid = s + (e - s) // 2
        if x <= mid:
            update(x, v, 2*node, s, mid)
        else:
            update(x, v, 2*node+1, mid+1, e)
        tree[node] = tree[2*node] * tree[2*node+1] % DIV

    def query(l, r, node, s, e):
        if r < s or l > e:
            return 1
        elif l <= s and e <= r:
            return tree[node]

        mid = s + (e - s) // 2
        return query(l, r, 2*node, s, mid) * query(l, r, 2*node+1, mid+1, e) % DIV

    n, m, k = map(int, input().split())
    num_list = [int(input()) for num in range(n)]
    size = 1 << ceil(log2(n))
    num_list.extend([1] * (size - n))
    tree = [0] * (2 * size)
    make_tree(1, 1, size)

    for command in range(m + k):
        a, b, c = map(int, input().split())
        if a == 1:
            update(b, c, 1, 1, size)
        else:
            print(query(b, c, 1, 1, size))


main()
