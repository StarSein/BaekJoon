import sys


input = sys.stdin.readline

NUM_DIR = 4
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

COL = 0
ROW = 1


def solution() -> str:
    for col in range(h):
        for row in range(w):
            if building[col][row] == '@':
                INIT_SITE = (col, row)
                break
    man_sites = [INIT_SITE]
    edge_fire_sites = []
    for col in range(h):
        for row in range(w):
            if building[col][row] == '*':
                edge_fire_sites.append((col, row))


    time = 1
    is_escaped = False
    while not is_escaped:
        if len(man_sites) == 0:
            return "IMPOSSIBLE"

        next_efs = []
        for i, fire in enumerate(edge_fire_sites):
            for dir in range(NUM_DIR):
                next_col = fire[COL] + dy[dir]
                next_row = fire[ROW] + dx[dir]
                if 0 <= next_col < h and 0 <= next_row < w:
                    if building[next_col][next_row] == '.' or building[next_col][next_row] == '@': # '@'이 있던 곳에 불길이 퍼지는 것은 계산에 무의미함
                        next_efs.append((next_col, next_row))
                        building[next_col][next_row] = '*'

        next_ms = []
        for i, man in enumerate(man_sites):
            for dir in range(NUM_DIR):
                next_col = man[COL] + dy[dir]
                next_row = man[ROW] + dx[dir]
                if 0 <= next_col < h and 0 <= next_row < w:
                    if building[next_col][next_row] == '.':
                        if next_col == 0 or next_col == h-1 or next_row == 0 or next_row == w-1:
                            is_escaped = True
                        else:
                            next_ms.append((next_col, next_row))
                            building[next_col][next_row] = '@'

        edge_fire_sites = next_efs[:]
        man_sites = next_ms[:]
        time += 1
    return str(time)


if __name__ == '__main__':
    t = int(input())
    for tc in range(t):
        w, h = map(int, input().split())
        building = [[row for row in input().rstrip()] for col in range(h)]
        sol = solution()
        print(sol)