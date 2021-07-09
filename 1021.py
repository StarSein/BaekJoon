import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())
l_num = list(map(int, sys.stdin.readline().split()))

q = deque([i for i in range(1, N + 1)])
k = 0
count = 0
while len(q) != N - M:
    if q[0] == l_num[k]:
        q.popleft()
        k += 1
    else:
        idx = q.index(l_num[k])
        if idx <= len(q) // 2:
            q.rotate(-idx)
            count += idx
        else:
            q.rotate(len(q) - idx)
            count += len(q) - idx
print(count)
