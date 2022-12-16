from sys import stdin
from collections import defaultdict
from typing import List, Tuple


def solution(opinions: List[Tuple[int, int]]) -> int:
    table = defaultdict(int)
    for t, a in opinions:
        table[(t, a)] += 1
    for t, a in table.keys():
        if t == 0 and any(x == 1 and y != a for x, y in table.keys()):
            table[(t, a)] += 1
    if list(table.values()).count(1) != 1:
        return -1
    position = [-1 for _ in range(9)]
    for (t, a), cnt in table.items():
        if cnt == 1:
            position[a - 1] = 1 - t
        else:
            position[a - 1] = t
    if position.count(1) != 1 and position.count(0) != 8:
        return -1
    elif position.count(1) == 1:
        return position.index(1) + 1
    else:
        return position.index(-1) + 1
    

def input():
    return stdin.readline().rstrip()


def read_input():
    return [tuple(map(int, input().split())) for _ in range(9)]


def main():
    print(solution(read_input()))


if __name__ == '__main__':
    main()
