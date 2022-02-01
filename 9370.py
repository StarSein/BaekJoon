import sys
import heapq
from typing import List


input = sys.stdin.readline


def solution() -> List[str]:
    dists = [0] * (n + 1)
    is_ables = [False] * (n + 1)
    heap = []
    heapq.heappush(heap, (s, 0, False))
    while len(heap):
        current_node, dist, is_able = heapq.heappop(heap)
        if dists[current_node] == 0:
            dists[current_node] = dist
            is_ables[current_node] = is_able
            for i in range(len(roads[current_node])):
                next_node = roads[current_node][i][0]
                next_dist = roads[current_node][i][1]
                if dists[next_node] == 0:
                    if (current_node == g and next_node == h) or (current_node == h and next_node == g):
                        is_able = True
                    heapq.heappush(heap, (next_node, dist + next_dist, is_able))
    res = []
    for idx, node in enumerate(candidates):
        if is_ables[node]:
            res.append(str(node))
    return sorted(res)


if __name__ == '__main__':
    t = int(input())
    for tc in range(t):
        n, m, t = map(int, input().split())
        s, g, h = map(int, input().split())
        roads = [[] for node in range(n + 1)]
        for road in range(m):
            a, b, d = map(int, input().split())
            roads[a].append((b, d))
            roads[b].append((a, d))
        candidates = [int(input()) for x in range(t)]
        sol = solution()
        print(' '.join(sol))
