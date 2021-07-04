import sys


def search(k):
    global det
    if k == len(l_blank):
        det = True
        for col in range(9):
            print(*matrix[col])
    else:
        for n in filter(lambda m: m not in matrix[l_blank[k][0]], range(1, 10)):
            for x in range(1, 10):
                if l_blank[k] in globals()["part{}".format(x)]:
                    if n not in [matrix[c[0]][c[1]] for c in globals()["part{}".format(x)]]:
                        if n not in [matrix[s][l_blank[k][1]] for s in range(9)]:
                            matrix[l_blank[k][0]][l_blank[k][1]] = n
                            search(k + 1)
                            if det:
                                return 0
                            matrix[l_blank[k][0]][l_blank[k][1]] = 0


matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(9)]

for a in range(3):
    for b in range(3):
        globals()["part{}".format(a + 1 + 3 * b)] = {(3 * a + c, 3 * b + r) for c in range(3) for r in range(3)}

l_blank = []
for i in range(9):
    for j in range(9):
        if matrix[i][j] == 0:
            l_blank.append((i, j))

det = False
search(0)
