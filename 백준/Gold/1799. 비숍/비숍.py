from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    size = int(input())
    board = [list(map(int, input().split())) for _ in range(size)]
    return size, board


def solution(size: int, board: List[List[int]]) -> int:
    limit = 2 * size - 1
    avail = [True for _ in range(limit)]

    whites = [[] for _ in range(limit)]
    for r in range(size):
        for c in range(size):
            if board[r][c]:
                whites[r + c].append(r)

    def dfs(pos: int) -> int:
        if pos == limit:
            return 0
        ret = 0
        flag = True
        for r in whites[pos]:
            c = pos - r
            idx = size - 1 - r + c
            if avail[idx]:
                flag = False
                avail[idx] = False
                ret = max(ret, 1 + dfs(pos + 1))
                avail[idx] = True
        if flag:
            ret = dfs(pos + 1)

        return ret
    return dfs(0)


if __name__ == '__main__':
    print(solution(*read_input()))
