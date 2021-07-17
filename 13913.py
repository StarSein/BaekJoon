import sys


N, K = map(int, sys.stdin.readline().split())
if N >= K:
    print(N - K)
else:
    dp = [[(N, [N])]]
    time = 1
    while True:
        dp.append([])
        for item in dp[-2]:
            if item[0] - 1 == K or item[0] + 1 == K or item[0] * 2 == K:
                print(time)
                print(*item[1], K)
                exit(0)
            dp[-1].append((item[0] - 1, item[1] + [item[0] - 1]))
            dp[-1].append((item[0] + 1, item[1] + [item[0] + 1]))
            dp[-1].append((item[0] * 2, item[1] + [item[0] * 2]))
        time += 1
