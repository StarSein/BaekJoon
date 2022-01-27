import sys
import heapq

input = sys.stdin.readline


def solution() -> int:
    heap = [(times[i], i) for i in range(1, n + 1) if precedes_cnt[i] == 0]
    heapq.heapify(heap)
    time = 0
    while len(heap):
        time, current = heapq.heappop(heap)
        for idx, follower in enumerate(followers[current]):
            precedes_cnt[follower] -= 1
            if precedes_cnt[follower] == 0:
                heapq.heappush(heap, (time + times[follower], follower))
    return time


if __name__ == '__main__':
    n = int(input())
    followers = [[] for i in range(n + 1)]
    precedes_cnt = [0] * (n + 1)
    times = [0] * (n + 1)
    for i in range(1, n + 1):
        inp = list(map(int, input().split()))
        times[i] = inp[0]
        precedes_cnt[i] = inp[1]
        for j in range(2, len(inp)):
            followers[inp[j]].append(i)
    sol = solution()
    print(sol)

