from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N = int(input())
    heights = list(map(int, input().split()))
    return N, heights


def solution(N: int, heights: List[int]) -> int:
    MAX_ANGLE = 2e9
    answer = 0
    for i in range(N):
        cur_cnt = 0
        prev_angle = MAX_ANGLE
        for j in range(i - 1, -1, -1):
            cur_angle = (heights[i] - heights[j]) / (i - j)
            if cur_angle < prev_angle:
                prev_angle = cur_angle
                cur_cnt += 1

        prev_angle = -MAX_ANGLE
        for j in range(i + 1, N):
            cur_angle = (heights[j] - heights[i]) / (j - i)
            if cur_angle > prev_angle:
                prev_angle = cur_angle
                cur_cnt += 1

        answer = max(answer, cur_cnt)
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
