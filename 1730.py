import sys
from collections import deque


input = sys.stdin.readline
START_POINT = (1, 1)

cmdTable = {
    'U': {"col": -1, "row": 0,  "isVertical": True},
    'D': {"col": 1,  "row": 0,  "isVertical": True},
    'L': {"col": 0,  "row": -1, "isVertical": False},
    'R': {"col": 0,  "row": 1,  "isVertical": False}
}
COL = 0
ROW = 1

NOT_PASSED = chr(46)
ONLY_VERT = chr(124)
ONLY_HORIZON = chr(45)
BOTH_DIR = chr(43)


def leaveTrace(isVertical, currentTrace):
    if isVertical:
        newTrace = {NOT_PASSED: ONLY_VERT,
                    ONLY_VERT: ONLY_VERT,
                    ONLY_HORIZON: BOTH_DIR,
                    BOTH_DIR: BOTH_DIR}.get(currentTrace)
    else:
        newTrace = {NOT_PASSED: ONLY_HORIZON,
                    ONLY_VERT: BOTH_DIR,
                    ONLY_HORIZON: ONLY_HORIZON,
                    BOTH_DIR: BOTH_DIR}.get(currentTrace)
    return newTrace


def solution(n, movements):
    matrix = [[NOT_PASSED for i in range(n+1)] for j in range(n+1)]
    # idx : 1~n
    qMovements = deque(movements)
    current = START_POINT
    while qMovements:
        move = qMovements.popleft()
        next = (current[COL] + cmdTable[move]["col"], current[ROW] + cmdTable[move]["row"])
        if not (1 <= next[COL] <= n and 1 <= next[ROW] <= n):
            continue

        isVertical = cmdTable[move]["isVertical"]
        matrix[current[COL]][current[ROW]] = leaveTrace(isVertical, matrix[current[COL]][current[ROW]])
        matrix[next[COL]][next[ROW]] = leaveTrace(isVertical, matrix[next[COL]][next[ROW]])

        current = next

    for col in range(1, n+1):
        for row in range(1, n+1):
            print(matrix[col][row], end='')
        print()


if __name__ == '__main__':
    n = int(input())
    movements = input().rstrip()
    solution(n, movements)
