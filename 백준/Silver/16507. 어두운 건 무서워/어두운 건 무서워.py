import sys


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    R, C, Q = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(R)]
    pSum = [[0 for col in range(C + 1)] for row in range(R + 1)]

    for r in range(R):
        for c in range(C):
            pSum[r+1][c+1] = grid[r][c]

    for r in range(1, R + 1):
        for c in range(1, C):
            pSum[r][c+1] += pSum[r][c]

    for c in range(1, C + 1):
        for r in range(1, R):
            pSum[r+1][c] += pSum[r][c]

    for query in range(Q):
        r1, c1, r2, c2 = map(int, input().split())
        curSum = pSum[r2][c2] + pSum[r1-1][c1-1] - pSum[r2][c1-1] - pSum[r1-1][c2]
        curNum = (r2 - r1 + 1) * (c2 - c1 + 1)

        print(curSum // curNum)


if __name__ == '__main__':
    solution()
    