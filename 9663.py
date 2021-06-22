import sys
from math import perm


N = int(sys.stdin.readline())
num_case = 0
case = []
ll_check = [[False] * N] * N


def dfs(y, x, count):
    global num_case, ll_check
    if count == N:
        num_case += 1
        return

    for j in range(N):
        for i in range(N):
            if ll_check[j][i]:
                continue

            for p in range(N):
                ll_check[j][p] = True
            for q in range(N):
                ll_check[q][i] = True
            for b in range(N):
                for a in range(N):
                    if b + a == j + i or b - a == j - i:
                        ll_check[b][a] = True
            case.append((j, i))
            dfs(y, x, count + 1)
            for p in range(N):
                ll_check[j][p] = False
            for q in range(N):
                ll_check[q][i] = False
            for b in range(N):
                for a in range(N):
                    if b + a == j + i or b - a == j - i:
                        ll_check[b][a] = False
            case.pop()


for n in range(N):
    for m in range(N):
        dfs(n, m, 0)

if num_case:
    print(num_case//perm(num_case, N))
else:
    print(0)
