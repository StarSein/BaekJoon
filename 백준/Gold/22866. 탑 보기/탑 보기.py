from sys import stdin
from collections import deque
from itertools import islice
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    heights = list(map(int, input().split()))
    return N, heights


def solution(N: int, heights: List[int]) -> None:
    def stack_procedure():
        height = heights[i - 1]
        while stack and stack[-1][1] <= height:
            stack.pop()
        sight_cnt[i] += len(stack)
        if stack:
            close_idx[i] = min(close_idx[i], stack[-1][0], key=lambda x: (abs(i - x), x))
        stack.append((i, height))
    stack = deque()
    sight_cnt = [0 for _ in range(N + 1)]
    INF = -int(1e6)
    close_idx = [INF for _ in range(N + 1)]
    for i in range(1, N + 1):
        stack_procedure()
    stack = deque()
    for i in range(N, 0, -1):
        stack_procedure()
    for ans1, ans2 in islice(zip(sight_cnt, close_idx), 1, N + 1):
        if ans2 == INF:
            print(ans1)
        else:
            print(ans1, ans2)


if __name__ == '__main__':
    solution(*read_input())
