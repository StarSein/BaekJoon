import sys


def product(col, row):
    prd = 0
    for i in range(M):
        prd += matrixA[col][i] * matrixB[i][row]
    return prd


N, M = map(int, sys.stdin.readline().split())
matrixA = [[] for _ in range(N)]
for col in range(N):
    matrixA[col] = list(map(int, sys.stdin.readline().split()))
M, K = map(int, sys.stdin.readline().split())
matrixB = [[] for _ in range(M)]
for col in range(M):
    matrixB[col] = list(map(int, sys.stdin.readline().split()))

matrixPrd = [[] for _ in range(N)]
for col in range(N):
    for row in range(K):
        matrixPrd[col].append(product(col, row))
    print(*matrixPrd[col])
