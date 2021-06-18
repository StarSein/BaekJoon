import sys


N, M = map(int, sys.stdin.readline().split())
l_num = [i + 1 for i in range(N)]
l_check = [False] * N
arr = []


def dfs(count):
    if count == M:
        print(*arr)
        return

    for i in range(N):
        if l_check[i]:
            continue

        l_check[i] = True
        arr.append(l_num[i])
        dfs(count + 1)
        l_check[arr.pop()-1] = False


dfs(0)