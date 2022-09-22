import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    N, K = map(int, input().split())
    jewels = [tuple(map(int, input().split())) for _ in range(N)]
    bags = [int(input()) for _ in range(K)]

    jewels.sort(key=lambda x: x[0])
    bags.sort()

    jewelAvailHeap = []
    i = 0
    ans = 0
    for capacity in bags:
        while i < len(jewels) and jewels[i][0] <= capacity:
            heapq.heappush(jewelAvailHeap, -jewels[i][1])
            i += 1
        if jewelAvailHeap:
            ans += (-1) * heapq.heappop(jewelAvailHeap)

    return ans


if __name__ == '__main__':
    print(solution())
