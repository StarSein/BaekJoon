from sys import stdin
from collections import defaultdict
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    N, C = map(int, input().split())
    M = int(input())
    boxes = [tuple(map(int, input().split())) for _ in range(M)]
    return N, C, M, boxes


def solution(N: int, C: int, M: int, boxes: List[Tuple[int, int, int]]) -> int:
    boxes.sort()
    boxes.append((N + 1, N + 1, 0))
    table = defaultdict(int)
    capa = C
    min_heap = []
    max_heap = []
    answer = 0
    for l, r, num in boxes:
        while min_heap and min_heap[0][0] <= l:
            val = table[heapq.heappop(min_heap)]
            answer += val
            capa += val
        if capa >= num:
            table[(r, l)] = num
            heapq.heappush(min_heap, (r, l))
            heapq.heappush(max_heap, (-r, l))
            capa -= num
            continue
        while capa < num and max_heap and -max_heap[0][0] > r:
            cr, cl = -max_heap[0][0], max_heap[0][1]
            if table[(cr, cl)] <= num - capa:
                capa += table[(cr, cl)]
                table[(cr, cl)] = 0
                heapq.heappop(max_heap)
            else:
                table[(cr, cl)] -= num - capa
                capa = num
        val = min(capa, num)
        if val:
            table[(r, l)] = val
            heapq.heappush(min_heap, (r, l))
            heapq.heappush(max_heap, (-r, l))
            capa -= val
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
