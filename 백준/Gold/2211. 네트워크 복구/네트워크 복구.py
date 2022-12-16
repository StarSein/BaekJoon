from sys import stdin
from typing import List, Tuple
import heapq


def solution(N: int, M: int, edges: List[Tuple[int, int]]) -> None:
    graph = [[] for _ in range(N + 1)]
    for a, b, c in edges:
        graph[a].append((b, c))
        graph[b].append((a, c))
    recent = [-1 for _ in range(N + 1)]
    heap = [(0, 0, 1)]
    while heap:
        cost, prev, cur = heapq.heappop(heap)
        if recent[cur] != -1:
            continue
        recent[cur] = prev
        
        for nex, weight in graph[cur]:
            if recent[nex] == -1:
                heapq.heappush(heap, (cost + weight, cur, nex))
    answers = [(cur, recent[cur]) for cur in range(2, N + 1) if recent[cur] != -1]
    print(len(answers))
    for ans in answers:
        print(*ans)


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    return N, M, edges


def main():
    solution(*read_input())


if __name__ == '__main__':
    main()
    