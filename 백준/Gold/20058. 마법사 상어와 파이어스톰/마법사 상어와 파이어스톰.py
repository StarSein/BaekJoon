import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    def rotate(interval: int):
        for col in range(0, size, interval):
            for row in range(0, size, interval):
                for c in range(interval):
                    for r in range(interval):
                        tmp[col+c][row+r] = ice[col+interval-1-r][row+c]

    def count_neightbor_ice(c: int, r: int) -> int:
        cnt = 0
        for dy, dx in zip(y_steps, x_steps):
            nc, nr = c + dy, r + dx
            if 0 <= nc < size and 0 <= nr < size and tmp[nc][nr]:
                cnt += 1
        return cnt

    def melt():
        for col in range(size):
            for row in range(size):
                ice[col][row] = tmp[col][row]
                if ice[col][row] and count_neightbor_ice(col, row) < 3:
                    ice[col][row] -= 1

    def get_sum():
        return sum([sum(col) for col in ice])

    def get_area(c: int, r: int) -> int:
        dq = deque([(c, r)])
        visited[c][r] = True
        area = 1
        while dq:
            col, row = dq.popleft()
            for dy, dx in zip(y_steps, x_steps):
                nc, nr = col + dy, row + dx
                if 0 <= nc < size and 0 <= nr < size and ice[nc][nr] and not visited[nc][nr]:
                    dq.append((nc, nr))
                    area += 1
                    visited[nc][nr] = True
        return area

    def get_max_area() -> int:
        max_area = 0
        for col in range(size):
            for row in range(size):
                if ice[col][row] and not visited[col][row]:
                    max_area = max(max_area, get_area(col, row))
        return max_area

    n, q = map(int, input().split())
    size = 2 ** n
    ice = [list(map(int, input().split())) for _ in range(size)]
    l_list = list(map(int, input().split()))
    y_steps = [0, 1, 0, -1]
    x_steps = [1, 0, -1, 0]
    tmp = [[0 for row in range(size)] for col in range(size)]
    for l in l_list:
        rotate(2 ** l)
        melt()

    visited = [[False for row in range(size)] for col in range(size)]
    print(get_sum(), get_max_area(), sep='\n')


if __name__ == '__main__':
    main()
