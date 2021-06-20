import sys

N, M = map(int, sys.stdin.readline().split())
l_num = sorted(list(map(int, sys.stdin.readline().split())))
arr = []


def dfs(count):
    if count == M:
        print(*arr)
        return

    for i in range(N):
        if len(arr) and l_num[i] < arr[-1]:
            continue

        arr.append(l_num[i])
        dfs(count + 1)
        arr.pop()


dfs(0)
