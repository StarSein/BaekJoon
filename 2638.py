import sys


def bfs(col, row):
    visited[col][row] = True
    stack = [(col, row)]
    while len(stack):
        temp = []
        for spot in stack:
            for step in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                tup_coord = (spot[0] + step[0], spot[1] + step[1])
                if 0 <= tup_coord[0] < N and 0 <= tup_coord[1] < M:
                    if matrix[tup_coord[0]][tup_coord[1]]:
                        if tup_coord in exposure:
                            melting.add(tup_coord)
                        else:
                            exposure.add(tup_coord)
                    elif visited[tup_coord[0]][tup_coord[1]]:
                        continue
                    else:
                        temp.append(tup_coord)
                        visited[tup_coord[0]][tup_coord[1]] = True
        stack = temp[:]


N, M = map(int, sys.stdin.readline().split())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
time = 0
while True:
    visited = [[False for i in range(M)] for j in range(N)]
    exposure = set()
    melting = set()
    bfs(0, 0)
    if not melting:
        break
    for cheese in melting:
        matrix[cheese[0]][cheese[1]] = 0
    time += 1
print(time)
