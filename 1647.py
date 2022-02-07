import sys
import heapq


input = sys.stdin.readline


def solution():
    heapq.heapify(min_heap)
    mst_list = []
    while len(min_heap):
        cost, node_a, node_b = heapq.heappop(min_heap)

        a_root = find_root(node_a)
        b_root = find_root(node_b)
        if a_root == b_root:
            continue

        union(a_root, b_root)
        mst_list.append(cost)

    res = sum(mst_list) - max(mst_list)
    print(res)


def find_root(x: int) -> int:
    if roots[x] == x:
        return x

    roots[x] = find_root(roots[x])
    return roots[x]


def union(a_root: int, b_root: int):
    roots[b_root] = a_root


if __name__ == '__main__':
    n, m = map(int, input().split())
    roots = [num for num in range(n + 1)]
    min_heap = []
    for edge in range(m):
        a, b, c = map(int, input().split())
        heapq.heappush(min_heap, (c, a, b))
    solution()
