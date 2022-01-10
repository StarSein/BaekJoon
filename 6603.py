import sys
from typing import List

input = sys.stdin.readline
NUM_LOTTO = 6


def solution(elements: List[int]):
    collected = []
    latest_idx = -1
    collect_nums(latest_idx, collected, elements)


def collect_nums(latest_idx: int, collected: List[int], elements: List[int]):
    if len(collected) == NUM_LOTTO:
        for idx, val in enumerate(collected):
            print(val, end=' ')
        print()
        return

    for idx, val in enumerate(elements):
        if idx > latest_idx:
            collected.append(val)
            tmp_idx = latest_idx
            latest_idx = idx
            collect_nums(latest_idx, collected, elements)
            collected.pop()
            latest_idx = tmp_idx


if __name__ == '__main__':
    while True:
        inp = list(map(int, input().split()))
        k = inp[0]
        if k == 0:
            break
        elements = inp[1:]
        solution(elements)
        print()
