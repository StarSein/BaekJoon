import sys


D = int(sys.stdin.readline()) - 2
if D < 0:
    print(0)
    exit(0)
matrix = [[] for _ in range(7)]  # 전, 미, 신, 한, 진, 형, 학
matrix[0] = [0, 1, 1, 0, 0, 0, 0]
matrix[1] = [1, 0, 1, 1, 0, 0, 0]
matrix[2] = [1, 1, 0, 1, 1, 0, 0]
matrix[3] = [0, 1, 1, 0, 1, 1, 0]
matrix[4] = [0, 0, 1, 1, 0, 0, 1]
matrix[5] = [0, 0, 0, 1, 0, 0, 1]
matrix[6] = [0, 0, 0, 0, 1, 1, 0]

k = 2
while k <= D:
    temp = [[] for _ in range(7)]
    for c in range(7):
        for r in range(7):
            prd = 0
            for t in range(7):
                prd += (matrix[c][t] * matrix[t][r]) % 1000000007
            temp[c].append(prd % 1000000007)
    matrix = temp
    globals()['matrix_{}'.format(k)] = temp
    k <<= 1
D -= k
while D > 0:
    k >>= 1
    if D >= k:
        temp = [[] for _ in range(7)]
        for c in range(7):
            for r in range(7):
                prd = 0
                for t in range(7):
                    prd += (matrix[c][t] * globals()['matrix_{}'.format(k)][t][r]) % 1000000007
                temp[c].append(prd % 1000000007)
        matrix = temp
        D -= k
print((matrix[0][0] + matrix[0][1] + matrix[1][0] + matrix[1][1]) % 1000000007)
