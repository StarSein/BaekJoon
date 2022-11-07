"""
최대 100만 개의 노드인데, 메모이제이션과 함께 백트래킹을 해도 될 지 모르겠다.
DP로 접근할 수 있지 않을까?
왼쪽, 오른쪽, 아래쪽 이동만 가능하니까 'ㄹ' 자로 bottom-up 방식의 구현이 가능하려나
'ㄹ'자는 안 되고,
각 행마다 왼쪽 끝에서 오른쪽 끝으로 한 번 쓸고, 오른쪽 끝에서 왼쪽 끝으로 한 번 쓸면 되겠다.
각 방향으로 쓸 때마다 독립된 dp를 사용해야 하고 위에서 내려오는 값도 보존해야 하므로,
row * col * 3 의 3차원 dp 배열을 사용하자.

try1) WA.
반례) https://www.acmicpc.net/board/view/18131
5 5
-100 -100 -100 -100 -100
-100 -100 -100 -100 -100
-100 -100 -100 -100 -100
-100 -100 -100 -100 -100
-100 -100 -100 -100 -100

정답: -900
출력: -500
원인) 맨 윗 줄의 누적합 처리를 0번 인덱스에 대해서만 해주고 있었다.
해결) 1, 2번 인덱스에 대해서도 해주자.
"""
from sys import stdin
from typing import Tuple


input = lambda: stdin.readline().rstrip()


def solution(n: int, m: int, grid: Tuple[Tuple[int, ...]]) -> int:
    dp = [[[grid[row][col]] * 3 for col in range(m)] for row in range(n)]

    for col in range(1, m):
        dp[0][col][0] += dp[0][col - 1][0]
        dp[0][col][1] += dp[0][col - 1][1]
        dp[0][col][2] += dp[0][col - 1][2]

    for row in range(1, n):
        for col in range(m):
            max_val = max(dp[row - 1][col])
            dp[row][col][0] += max_val

        dp[row][0][1] += max(dp[row - 1][0])
        for col in range(1, m):
            max_val = max(dp[row][col - 1][0], dp[row][col - 1][1])
            dp[row][col][1] += max_val

        dp[row][m - 1][2] += max(dp[row - 1][m - 1])
        for col in range(m - 2, -1, -1):
            max_val = max(dp[row][col + 1][0], dp[row][col + 1][2])
            dp[row][col][2] += max_val

    return max(dp[n - 1][m - 1])


if __name__ == '__main__':
    N, M = map(int, input().split())
    print(solution(N, M, tuple(tuple(map(int, input().split())) for _ in range(N))))
