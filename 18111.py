import sys
from typing import List, Tuple
from collections import defaultdict


input = sys.stdin.readline
MAX_HEIGHT = 256
MIN_HEIGHT = 0
INF = 200_000_000

TIME_DIG = 2
TIME_PILE = 1


def binary_search(n: int, m: int, b: int, site: List[List[int]]) -> Tuple[int, int]:
    min_height = MAX_HEIGHT
    max_height = MIN_HEIGHT

    height_dict = defaultdict(int)
    for col in range(n):
        for row in range(m):
            height = site[col][row]
            min_height = min(min_height, height)
            max_height = max(max_height, height)
            height_dict[height] += 1

    best_time = INF
    best_height = -1
    for current_height in range(min_height, max_height + 1):
        current_time = total_time(current_height, b, height_dict)
        if current_time == INF:
            break

        if current_time <= best_time:
            best_time = current_time
            best_height = current_height

    return best_time, best_height


def total_time(target_height: int, b: int, height_dict: dict) -> int:
    num_dig = 0
    num_pile = 0
    for init_height, cnt in height_dict.items():
        gap = target_height - init_height
        if gap > 0:
            num_pile += gap * cnt
        else:
            num_dig -= gap * cnt

    if num_pile > b + num_dig:
        return INF
    else:
        return num_dig * TIME_DIG + num_pile * TIME_PILE


def main():
    n, m, b = map(int, input().split())
    site = [list(map(int, input().split())) for col in range(n)]
    print(*binary_search(n, m, b, site))


if __name__ == '__main__':
    main()
