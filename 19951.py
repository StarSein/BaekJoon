import sys
import heapq
from typing import List, Tuple


input = sys.stdin.readline


def solution(n: int, default_heights: List[int], cmd_list: List[Tuple[int]]) -> List[str]:
    final_heights = []
    pos_heap = []
    for idx, val in enumerate(cmd_list):
        start_pos, end_pos, k = val[0], val[1], val[2]
        heapq.heappush(pos_heap, (start_pos, k))
        heapq.heappush(pos_heap, (end_pos + 1, -k))
    next_cmd = heapq.heappop(pos_heap)

    amount_change = 0
    for i in range(1, n+1):
        while i == next_cmd[0]:
            amount_change += next_cmd[1]
            if len(pos_heap):
                next_cmd = heapq.heappop(pos_heap)
            else:
                break
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
