import sys


N, K = map(int, sys.stdin.readline().split())
site_s = set()
if N >= K:
    time = N - K
else:
    i = N
    site_s.add(i * 2)
    time = 0
    while True:
        if K in site_s:
            break
        temp = set()
        for e in site_s:
            temp.add(e + 1)
            temp.add(e - 1)
        set_add = set()
        for el in temp:
            if el < K:
                set_add.add(el * 2)
        site_s = temp.union(set_add)
        time += 1
print(time)