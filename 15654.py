import sys


N, M = map(int, sys.stdin.readline().split())
l_num = sorted(list(map(int, sys.stdin.readline().split())))
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
        arr.pop()

        l_check[i] = False


dfs(0)