from sys import stdin, setrecursionlimit
from typing import Tuple

setrecursionlimit(10_000)
input = lambda: stdin.readline().rstrip()


def solution(n: int, m: int, board: Tuple[str]) -> int:
    visited = [[False] * m for _ in range(n)]
    dp = [[-1] * m for _ in range(n)]

    INF = 2501
    dir_tuple = ((0, 1), (1, 0), (0, -1), (-1, 0))

    def dfs(row: int, col: int) -> int:
        if row < 0 or row >= n or col < 0 or col >= m or board[row][col] == 'H':
            return 0
        if visited[row][col]:
            return INF
        if dp[row][col] != -1:
            return dp[row][col]

        visited[row][col] = True
        w = int(board[row][col])
        dp[row][col] = 1 + max(dfs(row + dr * w, col + dc * w) for dr, dc in dir_tuple)
        visited[row][col] = False
        return dp[row][col]

    ans = dfs(0, 0)
    if ans >= INF:
        ans = -1
    return ans


if __name__ == '__main__':
    N, M = map(int, input().split())
    print(solution(N, M, tuple(input() for row in range(N))))
