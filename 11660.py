import sys


N, M = map(int, sys.stdin.readline().split())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

dp = [[] for _ in range(N)]
dp[0] = matrix[0]

for col in range(1, N):
    for row in range(N):
        dp[col].append(dp[col - 1][row] + matrix[col][row])

for col in range(N):
    for row in range(1, N):
        dp[col][row] = dp[col][row - 1] + dp[col][row]

for req in range(M):
    x1, y1, x2, y2 = map(lambda x: int(x) - 1, sys.stdin.readline().split())
    if x1 != 0 and y1 != 0:
        result = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1]
    elif y1 != 0:
        result = dp[x2][y2] - dp[x2][y1 - 1]
    elif x1 != 0:
        result = dp[x2][y2] - dp[x1 - 1][y2]
    else:
        result = dp[x2][y2]
    print(result)
