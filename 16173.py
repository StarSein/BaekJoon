import sys
from collections import deque


input = sys.stdin.readline
START_POINT = (0, 0)
VAL_OF_FINISH_SPOT = -1


def solution(n, matrix):
    loc_queue = deque()
    loc_queue.append(START_POINT)
    while loc_queue:
        col, row = loc_queue.pop()
        if matrix[col][row] == VAL_OF_FINISH_SPOT:
            return "HaruHaru"

        stepSize = matrix[col][row]
        if stepSize == 0:
            continue
        if not isOutOfBound(n, col + stepSize):
            loc_queue.append((col + stepSize, row))
        if not isOutOfBound(n, row + stepSize):
            loc_queue.append((col, row + stepSize))
    return "Hing"


def isOutOfBound(n, idx):
    if idx >= n:
        return True
    else:
        return False


if __name__ == '__main__':
    n = int(input())
    matrix = [list(map(int, input().split())) for i in range(n)]
    sol = solution(n, matrix)
    print(sol)