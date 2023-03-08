from sys import stdin
from collections import deque
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    return N, M, edges


def solution(N: int, M: int, edges: List[Tuple[int, int]]) -> List[int]:
    in_cnt = [0 for _ in range(N + 1)]
    out_list = [[] for _ in range(N + 1)]
    for a, b in edges:
        in_cnt[b] += 1
        out_list[a].append(b)

    dq = deque(node for node in range(1, N + 1) if in_cnt[node] == 0)
    answer = [0 for _ in range(N)]
    for i in range(N):
        cur = dq.popleft()
        answer[i] = cur

        for nex in out_list[cur]:
            in_cnt[nex] -= 1
            if in_cnt[nex] == 0:
                dq.append(nex)
    return answer


if __name__ == '__main__':
    print(*solution(*read_input()))
