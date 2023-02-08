"""
1. 적의 좌표로부터 다익스트라를 이용해 각 타워까지의 최소 전송 횟수를 구한다
2. 에너지의 최댓값은 각 타워별 최소 전송 횟수를 k라고 할 때 기존 에너지를 2^k로 나눈 값
"""
from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N, R, D, X, Y = map(int, input().split())
    towers = [tuple(map(int, input().split())) for _ in range(N)]
    return N, R, D, X, Y, towers


def solution(N: int, R: int, D: int, X: int, Y: int, towers: List[Tuple[int, int]]) -> float:
    dists = [-1 for _ in range(N)]
    heap = [(0, i, x, y) for i, (x, y) in enumerate(towers)
            if (X - x) ** 2 + (Y - y) ** 2 <= R ** 2]
    while heap:
        dist, ci, cx, cy = heapq.heappop(heap)
        if dists[ci] != -1:
            continue
        dists[ci] = dist
        for i, (x, y) in enumerate(towers):
            if (cx - x) ** 2 + (cy - y) ** 2 <= R ** 2 and dists[i] == -1:
                heapq.heappush(heap, (dist + 1, i, x, y))
    answer = sum(D / (1 << dist) for dist in dists if dist != -1)
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
