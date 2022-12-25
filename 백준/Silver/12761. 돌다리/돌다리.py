from sys import stdin
from collections import deque


def input():
    return stdin.readline().rstrip()


def read_input():
    return map(int, input().split())


def solution(A: int, B: int, N: int, M: int) -> int:
    if N == M:
        return 0
    SZ = int(1e5) + 1
    visited = [False for _ in range(SZ)]
    dq = deque()
    dq.append(N)
    move_cnt = 0
    while dq:
        move_cnt += 1
        for _ in range(len(dq)):
            cur = dq.popleft()
            for step in (1, A, B):
                for nex in (cur + step, cur - step, cur * step):
                    if 0 <= nex < SZ and not visited[nex]:
                        if nex == M:
                            return move_cnt
                        dq.append(nex)
                        visited[nex] = True
    return -1


if __name__ == '__main__':
    print(solution(*read_input()))
