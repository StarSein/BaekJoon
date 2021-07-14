import sys


def divide(x):
    if x % 3 == 0:
        return x // 3
    elif x & 1 == 0:
        return x >> 1
    else:
        return x - 1


N = int(sys.stdin.readline())
if N == 1:
    print(0, 1, sep='\n')
else:
    dp = [[(divide(N), [N]), (N - 1, [N])]]
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
            dp[-1].append((divide(item[0]), item[1] + [item[0]]))
            dp[-1].append((item[0] - 1, item[1] + [item[0]]))
