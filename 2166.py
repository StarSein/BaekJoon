import sys


N = int(sys.stdin.readline())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
S = 1/2 * abs(sum([matrix[i][0] * matrix[i+1][1] - matrix[i][1] * matrix[i+1][0] for i in range(-1, N-1)]))
print(round(S, 1))
