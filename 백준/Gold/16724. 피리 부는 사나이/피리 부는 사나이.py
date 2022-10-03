"""
1. 'SAFE ZONE'을 어디 설치하는지는 중요치 않다. 최소 개수가 중요하다.
2. 'SAFE ZONE'의 최소 개수는 연결 요소의 개수와 같다.
3. 1000 X 1000 의 각 인덱스를 하나의 노드로 간주하면 O(NM)에 풀이 가능하다.
4. 다만 구현의 편의를 위해 단방향 간선을 양방향 간선으로 취급하자.
"""
from sys import stdin
from collections import deque
from typing import List


input = lambda: stdin.readline().rstrip()


def solution(N: int, M: int, grid: List[List[str]]) -> int:
    NUM_NODE = N * M
    graph = [[] for _ in range(NUM_NODE)]
    # graph[i]: 노드 i의 인접 노드들을 저장하는 리스트

    dir_dict = {'R': (0, 1),
                'D': (1, 0),
                'L': (0, -1),
                'U': (-1, 0)}

    """ grid 배열의 방향 정보를 바탕으로 각 노드들을 연결한다 """
    for r in range(N):
        for c in range(M):
            dr, dc = dir_dict[grid[r][c]]
            nr, nc = r + dr, c + dc
            nodeA = r * M + c
            nodeB = nr * M + nc
            graph[nodeA].append(nodeB)
            graph[nodeB].append(nodeA)

    """ _node 가 포함된 연결 요소들을 모두 찾아서 방문 처리하는 함수 """
    def find_component(_node: int):
        dq = deque()
        dq.append(node)
        visited[node] = True
        while dq:
            cur_node = dq.popleft()
            for next_node in graph[cur_node]:
                if not visited[next_node]:
                    dq.append(next_node)
                    visited[next_node] = True

    """ 방문하지 않은 모든 연결 요소를 탐색하면서 그 개수를 센다 """
    visited = [False] * NUM_NODE
    component_cnt = 0
    for node in range(NUM_NODE):
        if not visited[node]:
            find_component(node)
            component_cnt += 1

    return component_cnt


if __name__ == '__main__':
    _N, _M = map(int, input().split())
    _grid = [list(input()) for _ in range(_N)]
    print(solution(_N, _M, _grid))