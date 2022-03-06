import sys


N, K = map(int, sys.stdin.readline().split())
stuff = [(0, 0)]
for _ in range(N):
    W, V = map(int, sys.stdin.readline().split())
    stuff.append((W, V))
dp = [[] for _ in range(N+1)]
for idx in range(1, N+1):
    if len(dp[idx-1]) != 0:
        for item in dp[idx-1]:
            if item[0] + stuff[idx][0] <= K:
                dp[idx].append((item[0] + stuff[idx][0], item[1] + stuff[idx][1]))
            dp[idx].append(item)
    if stuff[idx][0] <= K:
        dp[idx].append(stuff[idx])
    if len(dp[idx]) != 0:
        dp[idx].sort(key= lambda x: (-x[1], x[0]))
        useful = [dp[idx][0]]
        val = dp[idx][0][1]
        for item_ in dp[idx]:
            if item_[1] != val:
                useful.append(item_)
                val = item_[1]
        dp[idx] = useful

max_val = 0
for case in dp[N]:
    if case[1] > max_val:
        max_val = case[1]
print(max_val)
