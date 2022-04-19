import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    dy = (0, 1, 0, -1)
    dx = (1, 0, -1, 0)

    def get_movement(_col: int, _row: int):
        visited_set = set()
        visit_dq = deque([(_col, _row)])
        visited_set.add((_col, _row))
        while visit_dq:
            cy, cx = visit_dq.popleft()
            for d in range(4):
                ny = cy + dy[d]
                nx = cx + dx[d]
                if 0 <= ny < n and 0 <= nx < n and (ny, nx) not in visited_set \
                    and l <= abs(ppl[cy][cx] - ppl[ny][nx]) <= r:
                    visited_set.add((ny, nx))
                    visit_dq.append((ny, nx))
        if len(visited_set) == 1:
            return
        avr_ppl = sum(ppl[y][x] for y, x in visited_set) // len(visited_set)
        for y, x in visited_set:
            changes[(y, x)] = avr_ppl

    n, l, r = map(int, input().split())
    ppl = [list(map(int, input().split())) for _ in range(n)]

    cnt_day = 0
    while True:
        changes = dict()
        for col in range(n):
            for row in range(n):
                if (col, row) not in changes:
                    get_movement(col, row)

        if not changes:
            break

        for (col, row), amount in changes.items():
            ppl[col][row] = amount
        cnt_day += 1
    print(cnt_day)


if __name__ == '__main__':
    main()
