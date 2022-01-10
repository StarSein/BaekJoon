import sys
import heapq
from typing import List, Tuple


input = sys.stdin.readline


def solution(n: int, default_heights: List[int], start_heap: List[Tuple[int]]) -> List[str]:
    final_heights = []
    heapq.heapify(start_heap)
    next_dig = heapq.heappop(start_heap)
    end_heap = []
    heapq.heappush(end_heap, (next_dig[1] + 1, next_dig[2]))
    next_cover = heapq.heappop(end_heap)
    amount_change = 0
    for i in range(1, n+1):
        while i == next_dig[0]:
            amount_change += next_dig[2]
            if len(start_heap):
                next_dig = heapq.heappop(start_heap)
                heapq.heappush(end_heap, (next_dig[1] + 1, next_dig[2]))
            else:
                break
        while i == next_cover[0]:
            amount_change -= next_cover[1]
            next_cover = heapq.heappop(end_heap)
        final_heights.append(str(default_heights[i-1] + amount_change))

    return final_heights


if __name__ == '__main__':
    n, m = map(int, input().split())
    default_heights = list(map(int, input().split()))
    cmd_list = []
    for i in range(m):
        cmd_list.append(tuple(map(int, input().split())))
    sol = solution(n, default_heights, cmd_list)
    print(' '.join(sol))
