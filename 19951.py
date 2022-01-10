import sys
from typing import List


input = sys.stdin.readline


def solution(default_h: List[int], change_h: List[int]) -> List[str]:
    result_h = [str(default_h[0] + change_h[0])]
    for i in range(1, len(change_h) - 1):
        change_h[i] += change_h[i-1]
        result_h.append(str(default_h[i] + change_h[i]))

    return result_h


if __name__ == '__main__':
    n, m = map(int, input().split())
    default_h = list(map(int, input().split()))
    change_h = [0] * (n + 1)
    for i in range(m):
        start_pos, end_pos, change = map(int, input().split())
        change_h[start_pos-1] += change
        change_h[end_pos] -= change
    sol = solution(default_h, change_h)
    print(' '.join(sol))
