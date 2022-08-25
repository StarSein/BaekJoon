import sys


def input():
    return sys.stdin.readline().rstrip()


def solution() -> str:
    N = int(input())

    cops = [tuple(map(int, input().split())) for i in range(N)]
    sx, sy = map(int, input().split())

    isNorth, isEast, isSouth, isWest = True, True, True, True
    for x, y in cops:
        absDiffX = abs(x - sx)
        absDiffY = abs(y - sy)
        if absDiffX == absDiffY:
            if x > sx:
                isEast = False
            else:
                isWest = False
            if y > sy:
                isNorth = False
            else:
                isSouth = False
        elif absDiffX > absDiffY:
            if x > sx:
                isEast = False
            else:
                isWest = False
        else:
            if y > sy:
                isNorth = False
            else:
                isSouth = False

    if isNorth or isEast or isSouth or isWest:
        return "YES"
    else:
        return "NO"


if __name__ == '__main__':
    print(solution())
