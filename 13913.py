import sys


N, K = map(int, sys.stdin.readline().split())
if N >= K:
    print(N - K)
else:
    dp = [[(N, [N])]]
    time = 1
    set_visit = set()
    set_visit.add(N)
    det = True
    while det:
        dp.append([])
        for item in dp[-2]:
            for new_num in [item[0] - 1, item[0] + 1, item[0] * 2]:
                if new_num not in set_visit:
                    if new_num == K:
                        det = False
                    else:
                        set_visit.add(new_num)
                        dp[-1].append((new_num, item[1] + [new_num]))
            if not det:
                print(time)
                print(*item[1], K)
                break
        time += 1
