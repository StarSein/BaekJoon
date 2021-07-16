import sys


D = int(sys.stdin.readline())
matrix = [[] for _ in range(8)]  # 정, 전, 미, 신, 한, 진, 형, 학
matrix[0] = [0, 1, 1, 0, 0, 0, 0, 0]
matrix[1] = [1, 0, 1, 1, 0, 0, 0, 0]
matrix[2] = [1, 1, 0, 1, 1, 0, 0, 0]
matrix[3] = [0, 1, 1, 0, 1, 1, 0, 0]
matrix[4] = [0, 0, 1, 1, 0, 1, 1, 0]
matrix[5] = [0, 0, 0, 1, 1, 0, 0, 1]
matrix[6] = [0, 0, 0, 0, 1, 0, 0, 1]
matrix[7] = [0, 0, 0, 0, 0, 1, 1, 0]

k = 2
while k <= D:
    temp = [[] for _ in range(8)]
    for c in range(8):
        for r in range(8):
            prd = 0
            for t in range(8):
                prd += (matrix[c][t] * matrix[t][r]) % 1000000007
            temp[c].append(prd % 1000000007)
    matrix = temp
    globals()['matrix_{}'.format(k)] = temp
    k <<= 1
D -= k
while D > 0:
    k >>= 1
    if D >= k:
        temp = [[] for _ in range(8)]
        for c in range(8):
            for r in range(8):
                prd = 0
                for t in range(8):
                    prd += (matrix[c][t] * globals()['matrix_{}'.format(k)][t][r]) % 1000000007
                temp[c].append(prd % 1000000007)
        matrix = temp
        D -= k
print(matrix[0][0])