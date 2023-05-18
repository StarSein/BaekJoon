from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, k = map(int, input().split())
    lines = [input(), input()]
    return N, k, lines


def solution(N: int, k: int, lines: List[str]) -> int:
    visit = [[False for j in range(N)] for i in range(2)]
    dq = deque()
    dq.append((0, 0))
    visit[0][0] = True
    time = 0
    while dq:
        size = len(dq)
        for _ in range(size):
            line_no, pos = dq.popleft()
            if pos + k >= N:
                return 1
            for nex_no, nex_pos in [(line_no, pos + 1), (line_no, pos - 1), (1 - line_no, pos + k)]:
                if nex_pos > time and lines[nex_no][nex_pos] == '1' and not visit[nex_no][nex_pos]:
                    dq.append((nex_no, nex_pos))
                    visit[nex_no][nex_pos] = True
        time += 1
    return 0


if __name__ == '__main__':
    print(solution(*read_input()))
