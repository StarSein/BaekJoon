import sys

T = int(sys.stdin.readline())
for _ in range(T):
    n = int(sys.stdin.readline())
    stk = [list(map(int, sys.stdin.readline().split())) for i in range(2)]
    dp = [[] for i in range(n)]
    dp[0] = [(stk[0][0], 1), (stk[1][0], 2), (0, 0)]
    for idx in range(1, n):
        for item in dp[idx-1]:
            dp[idx].append((item[0], 0))
            if item[1] == 0:
                dp[idx].append((item[0] + stk[0][idx], 1))
                dp[idx].append((item[0] + stk[1][idx], 2))
            elif item[1] == 1:
                dp[idx].append((item[0] + stk[1][idx], 2))
            else:
                dp[idx].append((item[0] + stk[0][idx], 1))
        dp[idx].sort(key=lambda x: (x[1], -x[0]))
        cdd = []
        det = 0
        for item_ in dp[idx]:
            if item_[1] == det:
                cdd.append(item_)
                det += 1
        dp[idx] = cdd
    dp[n-1].sort(key=lambda x: -x[0])
    print(dp[n-1][0][0])