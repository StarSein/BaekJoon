import sys


A, B = map(int, sys.stdin.readline().split())
dp = [[A]]
det = 0
while True:
    dp.append([])
    for item in dp[-2]:
        for new_num in [item * 2, int("%d1" % item)]:
            if new_num < B:
                dp[-1].append(new_num)
            elif new_num == B:
                det = 1
                break
            else:
                pass
        if det:
            break
    if det:
        print(len(dp))
        break
    if len(dp[-1]) == 0:
        print(-1)
        break
