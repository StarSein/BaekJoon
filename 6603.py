import sys
from typing import List
from itertools import combinations

input = sys.stdin.readline
NUM_LOTTO = 6


def solution(elements: List[str]):
    for case in combinations(elements, NUM_LOTTO):
        print(' '.join(case))
    print()


if __name__ == '__main__':
    while True:
        inp = list(map(int, input().split()))
        k = inp[0]
        if k == 0:
            break
        elements = []
        for i in range(1, k+1):
            elements.append(str(inp[i]))
        solution(elements)
