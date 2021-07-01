import sys
from collections import deque


N, S = map(int, sys.stdin.readline().split())
seq_N = list(map(int, sys.stdin.readline().split()))
if sum(seq_N) < S:
    print(0)
else:
    init_sum = 0
    idx = 0
    d = deque([])
    while init_sum < S:
        init_sum += seq_N[idx]
        d.append(seq_N[idx])
        idx += 1
    min_len = idx
    while init_sum - d[0] >= S:
        init_sum -= d.popleft()
        min_len -= 1
    part_sum = init_sum
    for i in range(idx, N):
        d.append(seq_N[i])
        part_sum += seq_N[i]
        part_sum -= d.popleft()
        while part_sum - d[0] >= S:
            part_sum -= d.popleft()
            min_len -= 1
    print(min_len)
