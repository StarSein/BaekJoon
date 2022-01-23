import sys
import heapq
from typing import List

input = sys.stdin.readline


def solution() -> List[str]:
    heap = [i for i in range(1, n + 1) if cnt_fronts_list[i] == 0]
    heapq.heapify(heap)
    res = []
    while len(heap):
        current_problem = heapq.heappop(heap)
        res.append(str(current_problem))
        for idx, val in enumerate(backs_list[current_problem]):
            cnt_fronts_list[val] -= 1
            if cnt_fronts_list[val] == 0:
                heapq.heappush(heap, val)

    return res


if __name__ == '__main__':
    n, m = map(int, input().split())
    backs_list = [[] for _ in range(n + 1)]
    cnt_fronts_list = [0] * (n + 1)
    for i in range(m):
        a, b = map(int, input().split())
        backs_list[a].append(b)
        cnt_fronts_list[b] += 1
    sol = solution()
    print(' '.join(sol))