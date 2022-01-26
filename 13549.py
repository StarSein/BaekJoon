import sys
import heapq


input = sys.stdin.readline
MAX_X = 100_000
INF = MAX_X
WALK_COST = 1
WARP_COST = 0


def solution() -> int:
    dists = [INF] * (MAX_X + 1)
    heap = [(0, n)]
    while len(heap):
        dist, x = heapq.heappop(heap)

        if x == k:
            return dist

        if 0 <= x-1 and dists[x-1] == INF:
            dists[x-1] = dist + WALK_COST
            heapq.heappush(heap, (dists[x-1], x - 1))
        if x+1 <= MAX_X and dists[x+1] == INF:
            dists[x+1] = dist + WALK_COST
            heapq.heappush(heap, (dists[x+1], x + 1))
        if x == 0:
            continue

        i = 2 * x
        while i <= MAX_X and dists[i] == INF:
            dists[i] = dist + WARP_COST
            heapq.heappush(heap, (dists[i], i))
            i *= 2


if __name__ == '__main__':
    n, k = map(int, input().split())
    sol = solution()
    print(sol)
