"""
try 1) WA
원인: 방문 순서에서 인접한 두 노드의 깊이 차는 1 이하여야 한다.
추가 보완: 재귀 깊이 제한을 늘림

try 2) WA
반례: https://www.acmicpc.net/board/view/105377
5
1 2
2 5
1 3
3 4
1 2 3 4 5
정답: 0
출력: 1
원인: 부모 레벨의 방문 순서에 부합하는 순서여야 한다.
해결: 방문 순서의 노드를 deque에 저장하여 참조하자.

try 3) WA
반례: https://www.acmicpc.net/board/view/33656
11
2 1
2 5
4 3
4 7
1 4
5 6
8 10
8 9
9 11
6 8
2 5 1 6 4 8 3 7 9 10 11
정답: 1
출력: 0
원인: 모든 자식을 방문한 부모 노드를 덱에서 제거하는 작업에 오류가 있음

try 4) WA
원인: 시작 정점은 1이어야 한다
"""

from sys import stdin, setrecursionlimit
from collections import deque
from typing import List, Tuple


setrecursionlimit(int(3e5))


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    edges = [tuple(map(int, input().split())) for _ in range(N - 1)]
    order = list(map(int, input().split()))
    return N, edges, order


def solution(N: int, edges: List[Tuple[int, int]], order: List[int]) -> int:
    graph = [[] for _ in range(N + 1)]
    for a, b in edges:
        graph[a].append(b)
        graph[b].append(a)
    child_cnt = [0 for _ in range(N + 1)]
    parent = [0 for _ in range(N + 1)]

    def dfs(cur: int, par: int):
        parent[cur] = par
        for nex in graph[cur]:
            if nex != par:
                child_cnt[cur] += 1
                dfs(nex, cur)

    ROOT = 1
    if order[0] != ROOT:
        return 0
    
    dfs(ROOT, 0)

    dq = deque()
    dq.append(ROOT)
    for i in range(1, N):
        while child_cnt[dq[0]] == 0:
            dq.popleft()

        cur_node = order[i]
        if parent[cur_node] != dq[0]:
            return 0
        child_cnt[dq[0]] -= 1
        dq.append(cur_node)
    return 1


if __name__ == '__main__':
    print(solution(*read_input()))
