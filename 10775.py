import sys
from collections import deque


input = sys.stdin.readline
CLOSED = 0


def solution():
    cnt = 0
    while len(q_dock):
        aircraft = q_dock.popleft()
        a_root = find_root(aircraft-1)
        b_root = find_root(aircraft)
        if b_root == CLOSED:
            break
        union(a_root, b_root)
        cnt += 1
    print(cnt)


def union(a_root: int, b_root: int):
    if b_root != a_root:
        roots[b_root] = a_root
    else:
        roots[a_root] = roots[a_root-1]


def find_root(x: int):
    if roots[x] == x:
        return x

    roots[x] = find_root(roots[x])
    return roots[x]


if __name__ == '__main__':
    g = int(input())
    p = int(input())
    q_dock = deque([int(input()) for i in range(p)])
    roots = [gate for gate in range(g + 1)]
    solution()
