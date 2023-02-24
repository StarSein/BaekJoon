from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    above = [list(map(int, input().split())) for _ in range(N)]
    front = list(map(int, input().split()))
    right = list(map(int, input().split()))
    return N, M, above, front, right


def solution(N: int, M: int, above: List[List[int]], front: List[int], right: List[int]):
    answer = [[0 for c in range(M)] for r in range(N)]
    for r in range(N):
        for c in range(M):
            if above[r][c] == 0:
                continue
            answer[r][c] = min(front[c], right[N - 1 - r])

    for c in range(M):
        height = max(answer[r][c] for r in range(N))
        if height != front[c]:
            print(-1)
            return
    for r in range(N):
        height = max(answer[r][c] for c in range(M))
        if height != right[N - 1 - r]:
            print(-1)
            return

    for row in answer:
        print(*row)


if __name__ == '__main__':
    solution(*read_input())
