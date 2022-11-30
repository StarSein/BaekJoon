from sys import stdin
from typing import List, Tuple
import heapq

def input():
    return stdin.readline().rstrip()


def read_input() -> Tuple[int, Tuple[int, int, int], int, List[Tuple[int, int, int]]]:
    N = int(input())
    loc_tup = tuple(map(int, input().split()))
    M = int(input())
    edge_list = [tuple(map(int, input().split())) for _ in range(M)]
    return N, loc_tup, M, edge_list


def solution(N: int, loc_tup: Tuple[int, int, int], M: int, edges: List[Tuple[int, int, int]]) -> int:
    graph = [[] for _ in range(N + 1)]
    for u, v, w in edges:
        graph[u].append((v, w))
        graph[v].append((u, w))

    INF = -1
    dists = [[INF, INF, INF] for _ in range(N + 1)]
    for i, friend in enumerate(loc_tup):
        heap = [(0, friend)]
        while heap:
            cd, cn = heapq.heappop(heap)
            if dists[cn][i] != INF:
                continue
            dists[cn][i] = cd

            for nn, w in graph[cn]:
                if dists[nn][i] == INF:
                    heapq.heappush(heap, (cd + w, nn))
    max_dist = 0
    answer = 0
    for i in range(1, N + 1):
        if (cur_dist := min(dists[i])) > max_dist:
            max_dist = cur_dist
            answer = i
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
