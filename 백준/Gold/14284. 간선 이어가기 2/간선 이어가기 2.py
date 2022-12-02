from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    n, m = map(int, input().split())
    edge_list = [tuple(map(int, input().split())) for _ in range(m)]
    s, t = map(int, input().split())
    return n, m, edge_list, s, t


def solution(n: int, m: int, edges: List[Tuple[int, int]], s: int, t: int) -> int:
    graph = [[] for _ in range(n + 1)]
    for a, b, c in edges:
        graph[a].append((b, c))
        graph[b].append((a, c))

    visited = [False for _ in range(n + 1)]
    heap = [(0, s)]
    while heap:
        dist, node = heapq.heappop(heap)
        if visited[node]:
            continue

        if node == t:
            return dist

        visited[node] = True
        for adj, w in graph[node]:
            if not visited[adj]:
                heapq.heappush(heap, (dist + w, adj))
    return -1


if __name__ == '__main__':
    print(solution(*read_input()))
