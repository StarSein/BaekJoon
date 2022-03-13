import sys
import heapq
from typing import Tuple

def input():
    return sys.stdin.readline().rstrip()


def dijkstra(start: int, end: int) -> Tuple[int, int]:
    heap = [(0, start)]
    visited_set = set()
    is_found = False
    while len(heap):
        total_time, curr_node = heapq.heappop(heap)

        if curr_node == end:
            if not is_found:
                is_found = True
                min_time = total_time
                cnt = 1
            elif total_time == min_time:
                cnt += 1
            else:
                break

        if curr_node in visited_set:
            continue

        visited_set.add(curr_node)
        if curr_node < end:
            heapq.heappush(heap, (total_time + 1, curr_node + 1))
            heapq.heappush(heap, (total_time + 1, curr_node * 2))
        if curr_node > 1:
            heapq.heappush(heap, (total_time + 1, curr_node - 1))

    return min_time, cnt


def main():
    n, k = map(int, input().split())
    if n < k:
        answer = dijkstra(n, k)
    else:
        answer = (n - k, 1)
    print(answer[0], answer[1], sep='\n')


if __name__ == '__main__':
    main()
