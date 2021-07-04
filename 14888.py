import sys


def operate(i):
    global max_res, min_res, res
    if i == N:
        max_res = max(max_res, res)
        min_res = min(min_res, res)
    for idx in range(4):
        if l_opr[idx]:
            l_opr[idx] -= 1
            temp = res
            if idx == 0:
                res += l_A[i]
            elif idx == 1:
                res -= l_A[i]
            elif idx == 2:
                res *= l_A[i]
            else:
                if res < 0:
                    res *= -1
                    res //= l_A[i]
                    res *= -1
                else:
                    res //= l_A[i]
            operate(i + 1)
            l_opr[idx] += 1
            res = temp


N = int(sys.stdin.readline())
l_A = list(map(int, sys.stdin.readline().split()))
l_opr = list(map(int, sys.stdin.readline().split()))
max_res, min_res = (-1) * 10**8, 10**8
res = l_A[0]
operate(1)
print(max_res, min_res, sep='\n')
