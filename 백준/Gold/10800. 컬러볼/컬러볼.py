from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    balls = [tuple((i, *map(int, input().split()))) for i in range(N)]
    return N, balls


def solution(N: int, balls: List[Tuple[int, int, int]]) -> List[int]:
    balls.sort(key=lambda x: x[2])
    sizes = [0 for _ in range(N + 1)]
    size_sum = 0
    answer = [0 for _ in range(N)]
    i = 0
    while i < N:
        std_size = balls[i][2]
        buffer = []
        while i < N and balls[i][2] == std_size:
            idx, c, s = balls[i]
            answer[idx] = size_sum - sizes[c]
            buffer.append((c, s))
            i += 1
        for c, s in buffer:
            sizes[c] += s
            size_sum += s
    return answer


if __name__ == '__main__':
    print(*solution(*read_input()), sep='\n')
