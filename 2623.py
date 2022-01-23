import sys
from collections import deque


input = sys.stdin.readline


def solution() -> str:
    queue = deque([i for i in range(1, n+1) if cnt_fronts_list[i] == 0])
    res = []
    while len(queue):
        new_singer = queue.popleft()
        res.append(str(new_singer))
        for idx, val in enumerate(backs_list[new_singer]):
            cnt_fronts_list[val] -= 1
            if cnt_fronts_list[val] == 0:
                queue.append(val)

    if len(res) == n:
        return '\n'.join(res)
    else:
        return '0'


if __name__ == '__main__':
    n, m = map(int, input().split())
    backs_list = [[] for _ in range(n + 1)]
    cnt_fronts_list = [0] * (n + 1)
    for i in range(m):
        inp = list(map(int, input().split()))
        order = inp[1:]
        for j in range(len(order) - 1):
            a, b = order[j], order[j+1]
            backs_list[a].append(b)
            cnt_fronts_list[b] += 1
    sol = solution()
    print(sol)
