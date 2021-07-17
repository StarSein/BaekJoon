import sys


N, K = map(int, sys.stdin.readline().split())
if N >= K:
    print(N - K)
    print(*[num for num in range(N, K - 1, -1)])
else:
    dp0 = [(N, [N])]
    time = 1
    set_visit = set()
    set_visit.add(N)
    det = True
    while det:
        dp1 = []
        for item in dp0:
            for new_num in [item[0] - 1, item[0] + 1, item[0] * 2]:
                if new_num not in set_visit and 0 <= new_num < K * 2:
                    if new_num == K:
                        det = False
                    else:
                        set_visit.add(new_num)
                        dp1.append((new_num, item[1] + [new_num]))
            if not det:
                print(time)
                print(*item[1], K)
                break
        time += 1
        dp0 = dp1
