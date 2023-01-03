"""
try 1) WA
원인: 조건 분기 오류
    - 한 원이 다른 원을 포함할 조건에 오류가 있다.
해결: r1, r2, d가 삼각형을 이루지 않을 조건과 동치다.
"""
from sys import stdin
import math


def input():
    return stdin.readline().rstrip()


def read_test_case():
    return map(float, input().split())


def solution(x1, y1, r1, x2, y2, r2) -> float:
    d = math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)
    if d >= r1 + r2:
        return 0.0
    elif max(r1, r2) >= min(r1, r2) + d:
        return math.pi * min(r1, r2) ** 2
    else:
        angle1 = math.acos((r1 ** 2 + d ** 2 - r2 ** 2) / (2 * d * r1))
        angle2 = math.acos((r2 ** 2 + d ** 2 - r1 ** 2) / (2 * d * r2))
        h = r1 * math.sin(angle1)

        area = (r1 ** 2) * angle1 + (r2 ** 2) * angle2 - d * h
        return round(area, 3)


if __name__ == '__main__':
    answer = solution(*read_test_case())
    print(f"{answer:.3f}")
