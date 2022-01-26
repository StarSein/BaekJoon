import sys
import heapq


input = sys.stdin.readline
MAX_X = 100_000
INF = MAX_X + 1
WALK_COST = 1
WARP_COST = 0


def solution() -> int:
    if n >= k:
        return n - k

    dists = [INF] * (2 * MAX_X + 1)
    heap = [(0, n)]
    while len(heap):
        dist, x = heapq.heappop(heap)

        if x == k:
            return dist

        if 0 <= x-1:
            if dists[x-1] > dist + WALK_COST:
                dists[x-1] = dist + WALK_COST
                heapq.heappush(heap, (dists[x-1], x - 1))
        if x+1 <= 2 * MAX_X:
            if dists[x+1] > dist + WALK_COST:
                dists[x+1] = dist + WALK_COST
                heapq.heappush(heap, (dists[x+1], x + 1))
        if x == 0:
            continue

        i = 2 * x
        while i <= 2 * MAX_X:
            if dists[i] > dist + WARP_COST:
                dists[i] = dist + WARP_COST
                heapq.heappush(heap, (dists[i], i))
            i *= 2


if __name__ == '__main__':
    n, k = map(int, input().split())
    sol = solution()
    print(sol)
