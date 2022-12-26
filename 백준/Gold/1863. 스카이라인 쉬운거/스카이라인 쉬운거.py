from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    n = int(input())
    points = [tuple(map(int, input().split())) for _ in range(n)]
    return n, points


def solution(n: int, points: List[Tuple[int, int]]) -> int:
    answer = 0
    stack = []
    for x, y in points:
        while stack and stack[-1] > y:
            stack.pop()
            answer += 1
        if y == 0 or stack and stack[-1] == y:
            continue
        stack.append(y)

    answer += len(stack)
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
    