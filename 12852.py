import sys


def operate(x):
    l_res = []
    if x % 3 == 0:
        l_res.append(x // 3)
    if x & 1 == 0:
        l_res.append(x >> 1)
    l_res.append(x - 1)
    return l_res


N = int(sys.stdin.readline())
if N == 1:
    print(0, 1, sep='\n')
else:
    dp = [[]]
    for res in operate(N):
        dp[-1].append((res, [N]))
    det_loop = True
    while True:
        dp[-1].sort(key=lambda x: (x[0], len(x[1])))
        num = dp[-1][0][0]
        l_useful = [dp[-1][0]]
        for item in dp[-1]:
            if item[0] != num:
                l_useful.append(item)
                num = item[0]
        dp[-1] = l_useful
        for item in dp[-1]:
            if item[0] == 1:
                print(len(item[1]))
                history = item[1][:]
                history.append(1)
                print(*history)
                det_loop = False
                break
        if not det_loop:
            break
        dp.append([])
        for item in dp[-2]:
            for res in operate(item[0]):
                dp[-1].append((res, item[1] + [item[0]]))
