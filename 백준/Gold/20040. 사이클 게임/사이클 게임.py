import sys


def input():
    return sys.stdin.readline().rstrip()


def find(a: int) -> int:
    if root[a] == a:
        return root[a]

    root[a] = find(root[a])
    return root[a]


def merge(a: int, b: int):
    if a > b:
        a, b = b, a

    root[b] = a


def solution() -> int:
    n, m = map(int, input().split())
    for i in range(1, m + 1):
        a, b = map(int, input().split())

        ra = find(a)
        rb = find(b)

        if ra == rb:
            return i
        else:
            merge(ra, rb)
    return 0


if __name__ == '__main__':
    MAX_N = int(5e5)
    root = [i for i in range(MAX_N)]
    print(solution())
