"""
2차원 배열에 일일히 구현하면 시간 초과가 뜰 것이다.
이동 가능한 지점만 유지하는 방식으로 구현하자.
간편하게 재귀 호출을 이용하자.
"""
import sys
from typing import Tuple


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    board = [input() for _ in range(n)]
    empty_zone = set()
    hole, blue, red = (-1, -1), (-1, -1), (-1, -1)
    for col in range(1, n - 1):
        for row in range(1, m - 1):
            if board[col][row] == '.':
                empty_zone.add((col, row))
            elif board[col][row] == 'O':
                hole = (col, row)
            elif board[col][row] == 'B':
                blue = (col, row)
            elif board[col][row] == 'R':
                red = (col, row)
    d = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    global res
    res = 11

    def rotate_board(cnt: int, b: Tuple[int, int], r: Tuple[int, int]):
        if cnt == 11:
            return

        for dy, dx in d:
            yb, xb = b
            yr, xr = r

            while (yb + dy, xb + dx) in empty_zone:
                yb += dy
                xb += dx
            empty_zone.add(b)
            tmp_b = (yb, xb)
            empty_zone.discard(tmp_b)
            while (yr + dy, xr + dx) in empty_zone:
                yr += dy
                xr += dx
            empty_zone.add(r)
            tmp_r = (yr, xr)
            empty_zone.discard(tmp_r)
            while (yb + dy, xb + dx) in empty_zone:
                yb += dy
                xb += dx
            empty_zone.add(b)
            empty_zone.add(tmp_b)
            empty_zone.discard((yb, xb))

            while (yr + dy, xr + dx) in empty_zone:
                yr += dy
                xr += dx
            empty_zone.add(r)
            empty_zone.add(tmp_r)
            empty_zone.discard((yr, xr))

            if (yb + dy, xb + dx) == hole:
                pass
            elif (yr + dy, xr + dx) == hole:
                empty_zone.add((yr, xr))
                nyb, nxb = yb, xb
                while (nyb + dy, nxb + dx) in empty_zone:
                    nyb += dy
                    nxb += dx

                if (nyb + dy, nxb + dx) != hole:
                    global res
                    res = min(res, cnt)
                empty_zone.discard((yr, xr))

            elif b == (yb, xb) and r == (yr, xr):
                pass
            else:
                rotate_board(cnt + 1, (yb, xb), (yr, xr))
            empty_zone.add((yb, xb))
            empty_zone.add((yr, xr))
            empty_zone.discard(b)
            empty_zone.discard(r)
    rotate_board(1, blue, red)
    print(res if res <= 10 else -1)


if __name__ == '__main__':
    main()
