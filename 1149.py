import sys


INF = sys.maxsize
N = int(sys.stdin.readline())
l_cost = []
for inp in range(N):
    l_cost.append(tuple(map(int, sys.stdin.readline().split())))

dp = [[] for i in range(N)]
dp[0] = [l_cost[0][0], l_cost[0][1], l_cost[0][2]]
for idx in range(1, N):
    cost_R, cost_G, cost_B = INF, INF, INF
    for i in range(3):
        for j in filter(lambda x: x != i, range(3)):
            if j == 0:
                cost_R = min(cost_R, dp[idx-1][i] + l_cost[idx][0])
            elif j == 1:
                cost_G = min(cost_G, dp[idx-1][i] + l_cost[idx][1])
            else:
                cost_B = min(cost_B, dp[idx-1][i] + l_cost[idx][2])
    dp[idx] = [cost_R, cost_G, cost_B]

print(min(dp[N-1]))