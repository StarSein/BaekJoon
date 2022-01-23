import sys
from collections import deque
from typing import List


input = sys.stdin.readline


def solution() -> List[str]:
    res = []
    queue = deque([i for i in range(1, n + 1) if cnt_fronts_list[i] == 0])
    while len(queue):
        new_stud = queue.popleft()
        res.append(str(new_stud))
        for idx, val in enumerate(backs_list[new_stud]):
            cnt_fronts_list[val] -= 1
            if cnt_fronts_list[val] == 0:
                queue.append(val)
    return res


if __name__ == '__main__':
    n, m = map(int, input().split())
    backs_list = [[] for _ in range(n + 1)]
    cnt_fronts_list = [0] * (n + 1)
    for cpr in range(m):
        a, b = map(int, input().split())
        backs_list[a].append(b)
        cnt_fronts_list[b] += 1
    sol = solution()
    print(' '.join(sol))
