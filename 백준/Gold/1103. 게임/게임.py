"""
1. 모든 경우의 수를 다 해보는 데 나름의 최적화를 해야 겠다.
2. 무한 루프를 잡으려면 bfs보다는 dfs를 하는 게 용이하겠다.
3. 사이클(무한 루프)에 속하지 않은 지점의 경우 메모이제이션을 통해 중복 탐색을 제거하자.

try1) WA
원인: 함수 종료 시점에 visited 배열의 방문 처리를 해제하지 않았다.

try2) RE
원인: RecursionError
해결: 제너레이터 말고 리스트 컴프리헨션을 사용하자.

try3) RE
원인: RecursionError.
재귀 깊이 제한을 5000으로 설정했는데, 각 함수마다 max 함수가 호출되면서
(2500개의 노드 방문) * 2 + (python의 기본 스택 깊이) > 5000 이 되어 발생한 오류 같다.  
해결: 재귀 깊이를 10000으로 늘리자. 
"""


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
        dp[row][col] = 1 + max([dfs(row + dr * w, col + dc * w) for dr, dc in dir_tuple])
        visited[row][col] = False
        return dp[row][col]

    ans = dfs(0, 0)
    if ans >= INF:
        ans = -1
    return ans


if __name__ == '__main__':
    N, M = map(int, input().split())
    print(solution(N, M, tuple(input() for row in range(N))))
