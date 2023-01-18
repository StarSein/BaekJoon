from sys import stdin
from collections import Counter
from typing import List, Tuple, NoReturn


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N = int(input())
    boxes = [tuple(map(lambda x: int(x) + 500, input().split())) for _ in range(N)]
    return N, boxes


def solution(N: int, boxes: List[Tuple[int, int, int, int]]) -> int:
    def find_root(x: int) -> int:
        if roots[x] != x:
            roots[x] = find_root(roots[x])
        return roots[x]

    def union(a: int, b: int) -> NoReturn:
        if a > b:
            a, b = b, a
        roots[b] = a
        
    def procedure() -> NoReturn:
        if grid[y][x]:
            old_root = find_root(grid[y][x])
            new_root = find_root(i)
            if old_root != new_root:
                union(old_root, new_root)
        else:
            grid[y][x] = i

    roots = [i for i in range(N + 1)]
    grid = [[0 for x in range(1001)] for y in range(1001)]
    for i, (x1, y1, x2, y2) in enumerate(boxes, start=1):
        if x1 > x2:
            x1, x2 = x2, x1
        if y1 > y2:
            y1, y2 = y2, y1

        for y in range(y1, y2 + 1):
            for x in (x1, x2):
                procedure()
        for x in range(x1, x2 + 1):
            for y in (y1, y2):
                procedure()

    counter = Counter(find_root(x) for x in range(1, N + 1))
    answer = len(counter) + int(grid[500][500] == 0) - 1
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
