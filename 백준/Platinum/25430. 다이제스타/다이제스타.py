from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    S, E = map(int, input().split())
    return N, M, edges, S, E


def make_graph(N: int, edges: List[Tuple[int, int, int]]) -> List[List[Tuple[int, int]]]:
    graph = [[] for _ in range(N + 1)]
    for u, v, w in edges:
        graph[u].append((v, w))
        graph[v].append((u, w))
    return graph


def solution(N: int, M: int, edges: List[Tuple[int, int, int]], S: int, E: int) -> str:
    graph = make_graph(N, edges)
    visited = set()

    heap = [(0, S, 0, 0)]
    while heap:
        total_cost, cur_node, prev_node, prev_weight = heapq.heappop(heap)
        if (cur_node, prev_node) in visited:
            continue
        visited.add((cur_node, prev_node))
        if cur_node == E:
            return str(total_cost)
        for next_node, cur_weight in graph[cur_node]:
            if next_node != prev_node and cur_weight > prev_weight and (next_node, cur_node) not in visited:
                heapq.heappush(heap, (total_cost + cur_weight, next_node, cur_node, cur_weight))
    return "DIGESTA"


if __name__ == '__main__':
    print(solution(*read_input()))
