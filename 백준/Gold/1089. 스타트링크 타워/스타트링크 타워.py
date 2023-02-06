"""
5 * 3 크기의 격자를 길이 15의 비트마스크로 간주하면,
격자가 표시할 수 있는 숫자 = 해당 비트마스크를 포함하는 숫자
각 숫자 * 10^(자릿수 - 1) * 다른 자릿수에서 만들어질 수 있는 경우의 수 = 해당 숫자의 기여도
"""
from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N = int(input())
    board = [input() for _ in range(5)]
    return N, board


def solution(N: int, board: List[str]) -> str:
    def grid_to_mask(idx: int) -> int:
        str_mask = ''.join('1' if board[r][c] == '#' else '0'
                           for r in range(5) for c in range(4 * idx, 4 * idx + 3))
        return int(str_mask, 2)

    table = ["111101101101111", "001001001001001", "111001111100111",
             "111001111001111", "101101111001001", "111100111001111",
             "111100111101111", "111001001001001", "111101111101111", "111101111001111"]
    table = [int(x, 2) for x in table]
    cases = [[] for _ in range(N)]
    for i in range(N):
        cur_mask = grid_to_mask(i)
        for digit, bitmask in enumerate(table):
            if cur_mask & bitmask == cur_mask:
                cases[i].append(digit)

    total_num = 1
    for case in cases:
        total_num *= len(case)

    if total_num == 0:
        return "-1"

    total_sum = 0
    for i, case in enumerate(cases, start=1):
        for digit in case:
            total_sum += (10 ** (N - i)) * digit * (total_num // len(case))
    return str(total_sum / total_num)


if __name__ == '__main__':
    print(solution(*read_test_case()))
