import sys
import heapq

input = sys.stdin.readline


def solution():
    res = 0
    while len(min_heap):
        weight, node_a, node_b = heapq.heappop(min_heap)
        if find_root(node_a) == find_root(node_b):
            continue
        res += weight
        union(node_a, node_b)

    print(res)


def union(a: int, b: int):
    a_root = find_root(a)
    b_root = find_root(b)

    if a_root != b_root:
        roots[b_root] = a_root


def find_root(x: int) -> int:
    if roots[x] == x:
        return x

    roots[x] = find_root(roots[x])
    return roots[x]


if __name__ == '__main__':
    v, e = map(int, input().split())
    min_heap = []
    for edge in range(e):
        a, b, c = map(int, input().split())
        heapq.heappush(min_heap, (c, a, b))
    roots = [node for node in range(v + 1)]
    solution()
