import sys


N, M = map(int, sys.stdin.readline().split())
l_num = sorted(list(map(int, sys.stdin.readline().split())))
l_check = [False] * N
arr = []
l_arr = []
set_printed = set()


def dfs(count):
    if count == M:
        l_arr.append(' '.join(map(str, arr)))
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
for item in l_arr:
    if item not in set_printed:
        print(item)
        set_printed.add(item)
