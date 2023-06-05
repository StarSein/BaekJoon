from sys import stdin
from math import sqrt
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    n = int(input())
    stars = [tuple(map(float, input().split())) for _ in range(n)]
    return n, stars


def solution(n: int, stars: List[Tuple[float, float]]) -> float:
    graph = [[0.0 for c in range(n)] for r in range(n)]
    for i in range(n - 1):
        for j in range(i + 1, n):
            xi, yi = stars[i]
            xj, yj = stars[j]
            dist = sqrt((xi - xj) ** 2 + (yi - yj) ** 2)
            graph[i][j] = graph[j][i] = dist

    visit = [False for _ in range(n)]
    visit[0] = True
    heap = [(graph[0][i], i) for i in range(1, n)]
    heapq.heapify(heap)
    answer = 0.0
    while heap:
        cur_cost, cur_node = heapq.heappop(heap)

        if visit[cur_node]:
            continue

        visit[cur_node] = True
        answer += cur_cost

        for nex_node, nex_cost in enumerate(graph[cur_node]):
            heapq.heappush(heap, (nex_cost, nex_node))

    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
