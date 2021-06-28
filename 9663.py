import sys


def search(k):
    global cnt
    if k == N:
        cnt += 1
    else:
        for i in range(N):
            if col[i] | diag1[i + k] | diag2[i - k + N - 1]:
                continue
            col[i] = diag1[i + k] = diag2[i - k + N - 1] = True
            search(k + 1)
            col[i] = diag1[i + k] = diag2[i - k + N - 1] = False


N = int(sys.stdin.readline())
col = [False] * N
diag1 = [False] * (2 * N - 1)
diag2 = [False] * (2 * N - 1)
cnt = 0
search(0)
print(cnt)