import sys


N, M = map(int, sys.stdin.readline().split())
l_num = sorted(list(map(int, sys.stdin.readline().split())))
arr = []
l_arr = []
set_printed = set()


def dfs(count):
    if count == M:
        l_arr.append(' '.join(map(str, arr)))
        return

    for i in range(N):
        if arr and l_num[i] >= arr[-1]:
            arr.append(l_num[i])
        elif not arr:
            arr.append(l_num[i])
        else:
            continue
        dfs(count + 1)
        arr.pop()


dfs(0)
for item in filter(lambda x: x not in set_printed, l_arr):
    print(item)
    set_printed.add(item)
