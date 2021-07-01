import sys


N, S = map(int, sys.stdin.readline().split())
seq_N = list(map(int, sys.stdin.readline().split()))
cnt = 0
for length in range(1, N + 1):
    for idx in range(N - length + 1):
        if sum(seq_N[idx:idx + length]) == S:
            cnt += 1
print(cnt)