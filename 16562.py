import sys
from collections import deque


input = sys.stdin.readline


def solution() -> str:
    while len(friends):
        a, b = friends.popleft()
        union(a, b)

    total_cost = 0
    for idx, root in enumerate(roots):
        if idx + 1 == root:
            total_cost += costs[idx]

    if total_cost <= k:
        return str(total_cost)
    else:
        return "Oh no"


def find_root(x: int):
    if roots[x-1] == x:
        return x

    roots[x-1] = find_root(roots[x-1])
    return roots[x-1]


def union(a: int, b: int):
    a_root = find_root(a)
    b_root = find_root(b)

    if a_root != b_root:
        if costs[a_root-1] <= costs[b_root-1]:
            roots[b_root-1] = a_root
        else:
            roots[a_root-1] = b_root


if __name__ == '__main__':
    n, m, k = map(int, input().split())
    costs = list(map(int, input().split()))
    friends = deque()
    for i in range(m):
        friends.append(tuple(map(int, input().split())))

    roots = [i+1 for i in range(n)]
    sol = solution()
    print(sol)
