import sys
from typing import Tuple


input = sys.stdin.readline
MAX_ABS_SUM = 3_000_000_001


def solution() -> Tuple[int, int, int]:
    num_list.sort()
    min_abs_sum = MAX_ABS_SUM
    for lp in range(n - 2):
        mp, rp = lp + 1, n - 1
        while mp < rp:
            cur_sum = num_list[lp] + num_list[mp] + num_list[rp]
            cur_abs_sum = abs(cur_sum)
            if cur_abs_sum < min_abs_sum:
                min_abs_sum = cur_abs_sum
                best_mix = (num_list[lp], num_list[mp], num_list[rp])
                if min_abs_sum == 0:
                    return best_mix

            if cur_sum > 0:
                rp -= 1
            else:
                mp += 1

    return best_mix


if __name__ == '__main__':
    n = int(input())
    num_list = list(map(int, input().split()))

    print(*solution())
