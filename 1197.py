import sys
import heapq

input = sys.stdin.readline


def solution():
    is_checked = [False] * (v + 1)
    res = 0
    while len(min_heap):
        weight, node_a, node_b = heapq.heappop(min_heap)
        if is_checked[node_a] and is_checked[node_b]:
            continue
        res += weight
        is_checked[node_a] = True
        is_checked[node_b] = True

    print(res)


if __name__ == '__main__':
    v, e = map(int, input().split())
    min_heap = []
    for edge in range(e):
        a, b, c = map(int, input().split())
        heapq.heappush(min_heap, (c, a, b))
    solution()
