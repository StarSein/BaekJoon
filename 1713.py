import sys
import heapq
from collections import deque
from typing import List

input = sys.stdin.readline
MAX_ID = 100

VOTE_NUM = 0
POSTED_TIME = 1
ID = 2


def solution() -> List[int]:
    heap = []
    posted_time = 1
    is_posted = [False] * (MAX_ID + 1)
    while len(nominates):
        nominated_id = nominates.popleft()
        if is_posted[nominated_id]:
            for idx, val in enumerate(heap):
                if val[ID] == nominated_id:
                    val[VOTE_NUM] += 1
                    heapq.heapify(heap)
                    break
        else:
            new_info = [1, posted_time, nominated_id]
            if len(heap) < n:
                heapq.heappush(heap, new_info)
                is_posted[nominated_id] = True
            else:
                deleted_id = heapq.heappop(heap)[ID]
                is_posted[deleted_id] = False
                heapq.heappush(heap, new_info)
                is_posted[nominated_id] = True
            posted_time += 1
    return heap


if __name__ == '__main__':
    n = int(input())
    num_nominate = int(input())
    nominates = deque(map(int, input().split()))
    sol = solution()
    sol.sort(key=lambda x: x[ID])
    for idx, val in enumerate(sol):
        print(val[ID], end=' ')
