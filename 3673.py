import sys
import math
from collections import defaultdict


input = sys.stdin.readline


def solution() -> int:
    prefix_sums = [0]
    for idx, num in enumerate(num_list):
        prefix_sums.append((prefix_sums[-1] + num) % d)

    table = defaultdict(list)
    for idx, remainder in enumerate(prefix_sums):
        table[remainder].append(idx)

    cnt = 0
    for pos_list in table.values():
        length = len(pos_list)
        cnt += math.comb(length, 2)

    return cnt


if __name__ == '__main__':
    res = []
    c = int(input())
    for tc in range(c):
        d, n = map(int, input().split())
        num_list = list(map(int, input().split()))
        sol = solution()
        res.append(str(sol))
    print('\n'.join(res))
