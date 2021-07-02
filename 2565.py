import sys


N = int(sys.stdin.readline())
l_cable = [tuple(map(int, sys.stdin.readline().split())) for _ in range(N)]
l_cable.sort()
dp = [[] for _ in range(N)]
dp[0] = [(l_cable[0][1], 1), (0, 0)]
for idx in range(1, N):
    for item in dp[idx-1]:
        if item[0] < l_cable[idx][1]:
            dp[idx].append((l_cable[idx][1], item[1] + 1))
        dp[idx].append(item)
    dp[idx].sort(key=lambda x: (-x[1], x[0]))
    useful = [dp[idx][0]]
    length = dp[idx][0][1]
    for item_ in dp[idx]:
        if item_[1] < length:
            useful.append(item_)
            length = item_[1]
    dp[idx] = useful
LIS_len = dp[N-1][0][1]
print(N - LIS_len)