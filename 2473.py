import sys
from typing import Tuple

input = sys.stdin.readline


def solution() -> Tuple[int]:
    val_list.sort()
    lp, rp = 0, n - 1
    mp = binary_search(lp, rp)
    best_mix = (val_list[lp], val_list[mp], val_list[rp])
    best_sum = abs(sum(best_mix))
    while lp < rp - 2:
        mp_a = binary_search(lp + 1, rp)
        mp_b = binary_search(lp, rp - 1)
        next_mix_a = (val_list[lp + 1], val_list[mp_a], val_list[rp])
        next_mix_b = (val_list[lp], val_list[mp_b], val_list[rp - 1])
        next_sum_a = abs(sum(next_mix_a))
        next_sum_b = abs(sum(next_mix_b))
        if next_sum_a < next_sum_b:
            next_sum = next_sum_a
            next_mix = next_mix_a
            lp += 1
        else:
            next_sum = next_sum_b
            next_mix = next_mix_b
            rp -= 1

        if next_sum < best_sum:
            best_sum = next_sum
            best_mix = next_mix

    return best_mix


def binary_search(lp: int, rp: int) -> int:
    target = -(val_list[lp] + val_list[rp])
    start, end = lp + 1, rp - 1
    while start <= end:
        mid = start + (end - start) // 2
        if target < val_list[mid]:
            end = mid - 1
        elif target > val_list[mid]:
            start = mid + 1
        else:
            return mid

    candidates = []
    for dx in range(-1, 2, 1):
        cdd = mid + dx
        if lp < cdd < rp:
            candidates.append((abs(target - val_list[cdd]), cdd))
    candidates.sort()

    return candidates[0][1]


if __name__ == '__main__':
    n = int(input())
    val_list = list(map(int, input().split()))

    print(*solution())
