import sys


N = int(sys.stdin.readline())
l_cost = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
dp = [[] for _ in range(N)]
dp[0] = [(l_cost[0][0], 'RR'), (l_cost[0][1], 'GG'), (l_cost[0][2], 'BB')]
for idx in range(1, N - 1):
    for item in dp[idx-1]:
        if item[1][0] == 'R':
            dp[idx].append((item[0] + l_cost[idx][1], 'G%s' % item[1][1]))
            dp[idx].append((item[0] + l_cost[idx][2], 'B%s' % item[1][1]))
        elif item[1][0] == 'G':
            dp[idx].append((item[0] + l_cost[idx][0], 'R%s' % item[1][1]))
            dp[idx].append((item[0] + l_cost[idx][2], 'B%s' % item[1][1]))
        else:
            dp[idx].append((item[0] + l_cost[idx][0], 'R%s' % item[1][1]))
            dp[idx].append((item[0] + l_cost[idx][1], 'G%s' % item[1][1]))
    dp[idx].sort(key=lambda x: (x[1], x[0]))
    useful = [dp[idx][0]]
    lf_color = dp[idx][0][1]
    for case in dp[idx]:
        if case[1] != lf_color:
            useful.append(case)
            lf_color = case[1]
    dp[idx] = useful
res = []
for item in dp[N - 2]:
    for color in filter(lambda x: x not in item[1], ['R', 'G', 'B']):
        if color == 'R':
            res.append(item[0] + l_cost[N - 1][0])
        elif color == 'G':
            res.append(item[0] + l_cost[N - 1][1])
        else:
            res.append(item[0] + l_cost[N - 1][2])
print(min(res))
