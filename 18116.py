import sys
from collections import defaultdict
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    def find_root(x: int) -> int:
        if roots[x] == x:
            return x

        roots[x] = find_root(roots[x])
        return roots[x]

    def union(a: int, b: int):
        a_root = find_root(a)
        b_root = find_root(b)

        if a_root != b_root:
            if a_root > b_root:
                a_root, b_root = b_root, a_root

            roots[b_root] = a_root
            num_component[a_root] += num_component[b_root]

    MAX_I = int(1e6)
    n = int(input())
    roots = [i for i in range(MAX_I + 1)]
    num_component = [1] * (MAX_I + 1)
    for i in range(n):
        cmd = input()
        if cmd[0] == 'I':
            a, b = map(int, cmd.split()[1:])
            union(a, b)
        else:
            c = int(cmd.split()[1])
            print(num_component[find_root(c)])


if __name__ == '__main__':
    main()
