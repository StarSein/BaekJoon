import sys


input = sys.stdin.readline


def union(a: int, b: int):
    a_root = find_root(a)
    b_root = find_root(b)

    if a_root != b_root:
        roots[b_root] = a_root


def is_same_set(a: int, b: int) -> bool:
    a_root = find_root(a)
    b_root = find_root(b)

    return a_root == b_root


def find_root(x: int) -> int:
    if roots[x] == x:
        return x

    root = find_root(roots[x])
    roots[x] = root
    return root


if __name__ == '__main__':
    n, m = map(int, input().split())
    roots = [i for i in range(n + 1)]
    for inp in range(m):
        opr, a, b = map(int, input().split())
        if opr == 0:
            union(a, b)
        else:
            print({True: "YES",
                   False: "NO"}.get(is_same_set(a, b)))

