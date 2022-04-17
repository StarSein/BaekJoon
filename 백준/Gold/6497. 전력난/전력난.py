import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def find_root(i: int) -> int:
        if roots[i] == i:
            return i

        roots[i] = find_root(roots[i])
        return roots[i]

    def union(x: int, y: int):
        if x > y:
            x, y = y, x

        roots[y] = x

    while True:
        m, n = map(int, input().split())
        if m == 0 and n == 0:
            return

        roads = sorted(list(tuple(map(int, input().split())) for road in range(n)), key=lambda x: x[2])

        roots = [i for i in range(m)]
        saved_cost = 0
        for x, y, z in roads:
            root_x = find_root(x)
            root_y = find_root(y)
            if root_x != root_y:
                union(root_x, root_y)
            else:
                saved_cost += z
        print(saved_cost)


if __name__ == '__main__':
    main()
