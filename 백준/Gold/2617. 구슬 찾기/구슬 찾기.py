from sys import stdin
from collections import deque
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    return N, M, edges


def solution(N: int, M: int, edges: List[Tuple[int, int]]) -> int:

    def procedure(graph: List[List[int]]):

        def bfs(start: int) -> int:
            visit = [False for _ in range(N + 1)]
            cnt = 0
            dq = deque([start])
            visit[start] = True
            while dq:
                cur = dq.popleft()
                cnt += 1
                for nex in graph[cur]:
                    if visit[nex]:
                        continue
                    dq.append(nex)
                    visit[nex] = True
            return cnt

        for i in range(1, N + 1):
            if bfs(i) > MID:
                never_mid[i] = True

    MID = (N + 1) // 2
    digraph = [[] for _ in range(N + 1)]
    revgraph = [[] for _ in range(N + 1)]

    for heavy, light in edges:
        digraph[heavy].append(light)
        revgraph[light].append(heavy)

    never_mid = [False for _ in range(N + 1)]

    procedure(digraph)
    procedure(revgraph)

    return sum(never_mid)


if __name__ == '__main__':
    print(solution(*read_input()))
