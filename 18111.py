import sys
from typing import List, Tuple


input = sys.stdin.readline
MAX_HEIGHT = 256
MIN_HEIGHT = 0
INF = 200_000_000


def binary_search(n: int, m: int, b: int, site: List[List[int]]) -> Tuple[int, int]:
    min_height = MAX_HEIGHT
    max_height = MIN_HEIGHT

    for col in range(n):
        for row in range(m):
            min_height = min(min_height, site[col][row])
            max_height = max(max_height, site[col][row])

    best_time = INF
    best_height = -1
    start, end = min_height, max_height
    while start <= end:
        mid = start + (end - start) // 2
        current_time = total_time(mid, n, m, b, site)
        if current_time == INF:
            end = mid - 1
        else:
            start = mid + 1

        if current_time <= best_time:
            best_time = current_time
            best_height = mid

    return best_time, best_height


def total_time(height: int, n: int, m: int, b: int, site: List[List[int]]) -> int:
    num_dig = 0
    num_stack = 0
    for col in range(n):
        for row in range(m):
            gap = site[col][row] - height
            if gap > 0:
                num_dig += gap
            else:
                num_stack += -gap

    if num_stack > b + num_dig:
        return INF
    else:
        return num_stack * 1 + num_dig * 2


def main():
    n, m, b = map(int, input().split())
    site = [list(map(int, input().split())) for col in range(n)]
    print(*binary_search(n, m, b, site))


if __name__ == '__main__':
    main()
