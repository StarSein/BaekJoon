import sys
from typing import Tuple

sys.setrecursionlimit(200_500)
input = sys.stdin.readline

HEAD = 0


def solution() -> Tuple[int, int]:
    make_tree(r, HEAD)
    giga_node, len_column = find_giga(r, 0)
    max_len_branch = calc_max_branch(giga_node)
    return len_column, max_len_branch


def make_tree(current: int, parent: int):
    for adjacent, weight in connected[current]:
        if adjacent != parent:
            childs[current].append((adjacent, weight))
            make_tree(adjacent, current)


def find_giga(current: int, length: int) -> Tuple[int, int]:
    if len(childs[current]) != 1:
        return current, length

    single_child, weight = childs[current][0]
    return find_giga(single_child, length + weight)


def calc_max_branch(current: int) -> int:
    if len(childs[current]) == 0:
        return 0

    branches = []
    for child, weight in childs[current]:
        branches.append(weight + calc_max_branch(child))

    return max(branches)


if __name__ == '__main__':
    n, r = map(int, input().split())
    connected = [[] for node in range(n + 1)]
    childs = [[] for node in range(n + 1)]
    for edge in range(n - 1):
        a, b, d = map(int, input().split())
        connected[a].append((b, d))
        connected[b].append((a, d))

    print(*solution())
