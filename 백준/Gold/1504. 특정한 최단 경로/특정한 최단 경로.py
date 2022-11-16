"""
1 -> v1 -> v2 -> N
or
1 -> v2 -> v1 -> N

a -> b -> c 가 최단 경로라면 a -> b, b -> c도 최단 경로다.
따라서 각 구간에 대해 다익스트라를 이용하면 된다
"""

from sys import stdin
from typing import List, Tuple
import heapq


input = lambda: stdin.readline().rstrip()


def solution(n: int, e: int, edges: List[Tuple[int, int, int]], v1: int, v2: int) -> int:
    graph = [[] for node in range(n + 1)]
    for a, b, c in edges:
        graph[a].append((b, c))
        graph[b].append((a, c))

    def dijkstra(start: int, end: int) -> int:
        visited = [False] * (n + 1)
        heap = []
        heapq.heappush(heap, (0, start))
        while heap:
            cur_dist, cur_node = heapq.heappop(heap)
            if visited[cur_node]:
                continue
            visited[cur_node] = True
            if cur_node == end:
                return cur_dist

            for next_node, weight in graph[cur_node]:
                if not visited[next_node]:
                    heapq.heappush(heap, (cur_dist + weight, next_node))
        return -1

    START = 1
    END = N
    INF = int(1e6)

    sv1 = dijkstra(START, v1)
    v1v2 = dijkstra(v1, v2)
    v2e = dijkstra(v2, END)
    if sv1 == -1 or v1v2 == -1 or v2e == -1:
        dist1 = INF
    else:
        dist1 = sv1 + v1v2 + v2e

    sv2 = dijkstra(START, v2)
    v2v1 = v1v2
    v1e = dijkstra(v1, END)
    if sv2 == -1 or v2v1 == -1 or v1e == -1:
        dist2 = INF
    else:
        dist2 = sv2 + v2v1 + v1e

    ans = min(dist1, dist2)
    ans = -1 if ans == INF else ans
    return ans


if __name__ == '__main__':
    N, E = map(int, input().split())
    edge_list = [tuple(map(int, input().split())) for _ in range(E)]
    V1, V2 = map(int, input().split())
    print(solution(N, E, edge_list, V1, V2))
