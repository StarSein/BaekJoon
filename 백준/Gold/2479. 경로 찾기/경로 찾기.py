from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, K = map(int, input().split())
    codes = [input() for _ in range(N)]
    codes.insert(0, "")
    A, B = map(int, input().split())
    return N, K, codes, A, B


def solution(N: int, K: int, codes: List[str], A: int, B: int) -> List[int]:
    def haming_distance(code1: str, code2: str) -> int:
        cnt = 0
        for i in range(K):
            if code1[i] != code2[i]:
                cnt += 1
        return cnt

    dist = [[0 for i in range(N + 1)] for j in range(N + 1)]
    for i in range(1, N):
        for j in range(i + 1, N + 1):
            dist[i][j] = dist[j][i] = haming_distance(codes[i], codes[j])

    dq = deque()
    visit = [False for _ in range(N + 1)]
    last = [-1 for _ in range(N + 1)]

    dq.append(A)
    visit[A] = True
    while dq:
        cur = dq.popleft()

        if cur == B:
            ret = [B]
            while cur != A:
                cur = last[cur]
                ret.append(cur)
            ret.reverse()
            return ret

        for nex in range(1, N + 1):
            if dist[cur][nex] == 1 and not visit[nex]:
                dq.append(nex)
                visit[nex] = True
                last[nex] = cur
    return [-1]


if __name__ == '__main__':
    print(*solution(*read_input()))
