"""
400 * 4^5 = 약 40만

try 1) WA
반례:
2
16 16
16 16
정답: 64
출력: 16
원인: move 함수 구현 오류
try 2) WA
원인: move 함수 구현 오류
"""

from sys import stdin
from copy import deepcopy
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    n = int(input())
    board = [list(map(int, input().split())) for _ in range(n)]
    return n, board


def solution(n: int, board: List[List[int]]) -> int:
    def rotate(b: List[List[int]]) -> List[List[int]]:
        ret = [[0 for c in range(n)] for r in range(n)]
        for r in range(n):
            for c in range(n):
                ret[r][c] = b[c][n - 1 - r]
        return ret

    def move(b: List[List[int]]) -> List[List[int]]:
        for sr in range(n):
            for sc in range(n):
                r = sr
                flag = False
                while r > 0 and b[r - 1][sc] == 0:
                    flag = True
                    r -= 1
                if flag:
                    b[r][sc] = b[sr][sc]
                    b[sr][sc] = 0
        return b

    def merge(b: List[List[int]]) -> List[List[int]]:
        for sr in range(n - 1):
            for sc in range(n):
                if b[sr][sc] == b[sr + 1][sc]:
                    b[sr][sc] *= 2
                    for r in range(sr + 1, n - 1):
                        b[r][sc] = b[r + 1][sc]
                    b[n - 1][sc] = 0
        return b

    def dfs(cnt: int, b: List[List[int]]) -> int:
        if cnt == 5:
            return max(max(line) for line in b)
        ret = 0
        for _ in range(4):
            b = rotate(b)
            ret = max(ret, dfs(cnt + 1, merge(move(deepcopy(b)))))
        return ret

    return dfs(0, board)


if __name__ == '__main__':
    print(solution(*read_test_case()))
