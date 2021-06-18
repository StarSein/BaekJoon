import sys


N, M = map(int, sys.stdin.readline().split())

num_list = [i + 1 for i in range(N)]
check_list = [False] * N

arr = []


def dfs(cnt):

    if cnt == M:
        print(*arr)
        return

    for i in range(N):
        if check_list[i]:
            continue

        check_list[i] = True
        arr.append(num_list[i])
        dfs(cnt + 1)
        arr.pop()

        for j in range(i + 1, N):
            check_list[j] = False


dfs(0)
