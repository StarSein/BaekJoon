from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    n, d, c = map(int, input().split())
    dependencies = [tuple(map(int, input().split())) for _ in range(d)]
    return n, d, c, dependencies


def solution(n: int, d: int, c: int, dependencies: List[Tuple[int, int, int]]) -> Tuple[int, int]:
    digraph = [[] for _ in range(n + 1)]
    for a, b, s in dependencies:
        digraph[b].append((a, s))

    visited = [False for _ in range(n + 1)]
    heap = [(0, c)]
    answer2 = 0
    while heap:
        cur_time, cur_node = heapq.heappop(heap)
        if visited[cur_node]:
            continue
        visited[cur_node] = True
        answer2 = cur_time
        for next_node, weight in digraph[cur_node]:
            if not visited[next_node]:
                heapq.heappush(heap, (cur_time + weight, next_node))
    answer1 = sum(visited)
    return answer1, answer2


if __name__ == '__main__':
    t = int(input())
    for _ in range(t):
        print(*solution(*read_input()))
