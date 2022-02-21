import sys
from typing import List


input = sys.stdin.readline
MIN_LEN = 1
MAX_LEN = 2**31 - 1


def main():
    k, n = map(int, input().split())
    cable_list = [int(input()) for cable in range(k)]

    res = binary_search(cable_list, n)
    print(f'{res}')


def binary_search(cable_list: List[int], n: int) -> int:
    start, end = MIN_LEN, MAX_LEN

    best_len = 0
    while start <= end:
        mid = start + (end - start) // 2

        num_cable = 0
        for idx, cable_length in enumerate(cable_list):
            num_cable += (cable_length // mid)

        if num_cable < n:
            end = mid - 1
        else:
            best_len = max(best_len, mid)
            start = mid + 1

    return best_len


if __name__ == '__main__':
    main()
