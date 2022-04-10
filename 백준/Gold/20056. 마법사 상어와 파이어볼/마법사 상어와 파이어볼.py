"""
[1차 채점]
IndexError. s가 N보다 클 수 있음을 간과했다.

s의 값은 유지한 채, 파이어볼을 이동시킬 때에만, s % n만큼 이동시키면 된다.
"""

import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m, k = map(int, input().split())
    matrix = [[[] for col in range(n)] for row in range(n)]
    for fireball in range(m):
        r, c, m, s, d = map(int, input().split())
        matrix[r-1][c-1].append((m, s, d))

    dy = [-1, -1, 0, 1, 1, 1, 0, -1]
    dx = [0, 1, 1, 1, 0, -1, -1, -1]

    def in_bound(pos: int) -> int:
        if pos < 0:
            pos += n
        elif pos >= n:
            pos -= n
        return pos

    for move in range(k):
        new_matrix = [[[] for col in range(n)] for row in range(n)]
        for row in range(n):
            for col in range(n):
                for m, s, d in matrix[row][col]:
                    nr, nc = row + dy[d] * (s % n), col + dx[d] * (s % n)
                    nr, nc = in_bound(nr), in_bound(nc)
                    new_matrix[nr][nc].append((m, s, d))

        for row in range(n):
            for col in range(n):
                fireballs = new_matrix[row][col]
                if len(fireballs) >= 2:
                    sum_m, sum_s, cnt_odd = 0, 0, 0
                    for m, s, d in fireballs:
                        sum_m += m
                        sum_s += s
                        cnt_odd += d % 2
                    new_m = sum_m // 5
                    if new_m == 0:
                        fireballs.clear()
                        continue

                    new_s = sum_s // len(fireballs)

                    if cnt_odd == len(fireballs) or cnt_odd == 0:
                        fireballs.clear()
                        fireballs.extend([(new_m, new_s, new_d) for new_d in range(0, 7, 2)])
                    else:
                        fireballs.clear()
                        fireballs.extend([(new_m, new_s, new_d) for new_d in range(1, 8, 2)])

        matrix = new_matrix
    sum_mass = 0
    for row in range(n):
        for col in range(n):
            for m, s, d in matrix[row][col]:
                sum_mass += m

    print(sum_mass)


if __name__ == '__main__':
    main()
