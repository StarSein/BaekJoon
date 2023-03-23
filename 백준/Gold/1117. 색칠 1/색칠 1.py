from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return map(int, input().split())


def solution(W: int, H: int, f: int, c: int, x1: int, y1: int, x2: int, y2: int) -> int:
    color_area = (x2 - x1)
    if 2 * f <= W:
        color_area += max(0, min(f + x2, 2 * f) - (f + x1))
    else:
        color_area += max(0, min(f + x2, W) - (f + x1))
    color_area *= (y2 - y1) * (c + 1)

    return W * H - color_area


if __name__ == '__main__':
    print(solution(*read_input()))
