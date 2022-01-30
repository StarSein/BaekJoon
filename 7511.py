import sys
from collections import deque
from typing import List


input = sys.stdin.readline


def solution() -> List[str]:
    while len(relations):
        a, b = relations.popleft()
        union(a, b)

    res = []
    while len(requests):
        u, v = requests.popleft()
        res.append(is_connected(u, v))

    return res


def union(a: int, b: int):
    a_root = find_root(a)
    b_root = find_root(b)

    if a_root != b_root:
        roots[b_root] = a_root


def find_root(x: int) -> int:
    if roots[x] == x:
        return x

    roots[x] = find_root(roots[x])
    return roots[x]


def is_connected(a: int, b: int) -> str:
    a_root = find_root(a)
    b_root = find_root(b)

    return str(int(a_root == b_root))


if __name__ == '__main__':
    t = int(input())
    for tc in range(1, t + 1):
        n = int(input())
        k = int(input())
        relations = deque([tuple(map(int, input().split())) for relation in range(k)])
        m = int(input())
        requests = deque([tuple(map(int, input().split())) for request in range(m)])

        roots = [i for i in range(n)]
        sol = solution()

        print(f"Scenario {tc}:")
        print('\n'.join(sol))
        if tc < t:
            print()
