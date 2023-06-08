from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, L = map(int, input().split())
    lines = [list(map(int, input().split())) for _ in range(L)]
    S, E = map(int, input().split())
    return N, L, lines, S, E


def solution(N: int, L: int, lines: List[List[int]], S: int, E: int) -> int:
    if S == E:
        return 0
    
    graph = [[] for _ in range(N + 1)]
    for line_id, line in enumerate(lines):
        line.pop(-1)
        for node in line:
            graph[node].append(line_id)

    dq = deque([(0, S)])
    visit = [False for _ in range(N + 1)]
    line_visit = [False for _ in range(L)]
    visit[S] = True
    while dq:
        cur_cost, cur_node = dq.popleft()

        for line_id in graph[cur_node]:
            if line_visit[line_id]:
                continue
            line_visit[line_id] = True
            for nex_node in lines[line_id]:
                if visit[nex_node]:
                    continue
                if nex_node == E:
                    return cur_cost
                dq.append((cur_cost + 1, nex_node))
                visit[nex_node] = True

    return -1


if __name__ == '__main__':
    print(solution(*read_input()))
