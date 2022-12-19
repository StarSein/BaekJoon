from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    meeting_list = [tuple(map(int, input().split())) for _ in range(N)]
    return N, meeting_list


def solution(N: int, meeting_list: List[Tuple[int, int, int]]) -> int:
    meeting_list.sort()
    max_total_num = 0
    heap = []
    for start, end, num in meeting_list:
        while heap and heap[0][0] <= start:
            max_total_num = max(max_total_num, heapq.heappop(heap)[1])
        heapq.heappush(heap, (end, max_total_num + num))
    while heap:
        max_total_num = max(max_total_num, heapq.heappop(heap)[1])
    return max_total_num


if __name__ == '__main__':
    print(solution(*read_input()))
