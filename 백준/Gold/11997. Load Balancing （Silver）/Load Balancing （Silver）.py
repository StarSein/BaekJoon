import sys


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    N = int(input())

    coord = []
    order = dict()
    inp = []
    for i in range(N):
        x, y = map(int, input().split())

        if x not in order:
            order[x] = 0
            coord.append(x)
        if y not in order:
            order[y] = 0
            coord.append(y)

        inp.append((x, y))

    coord.sort()
    for i in range(len(coord)):
        order[coord[i]] = i

    size = len(coord)
    grid = [[0 for col in range(size)] for row in range(size)]

    for x, y in inp:
        grid[order[y]][order[x]] = 1

    for r in range(size):
        for c in range(size - 1):
            grid[r][c+1] += grid[r][c]

    for c in range(size):
        for r in range(size - 1):
            grid[r+1][c] += grid[r][c]

    M = N
    for r in range(size - 1):
        for c in range(size - 1):
            num1 = grid[r][c]
            num2 = grid[size-1][c] - grid[r][c]
            num3 = grid[r][size-1] - grid[r][c]
            num4 = N - (num1 + num2 + num3)
            M = min(M, max(num1, num2, num3, num4))
    return M


if __name__ == '__main__':
    print(solution())
