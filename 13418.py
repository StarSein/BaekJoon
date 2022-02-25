import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    ascend_list = []
    for road in range(m + 1):
        a, b, c = map(int, input().split())
        ascend_list.append((c, a, b))
    ascend_list.sort()
    descend_list = ascend_list[:]
    descend_list.reverse()

    roots = [node for node in range(n + 1)]
    min_fatigue = mst_kruskal(descend_list, roots) ** 2

    roots = [node for node in range(n + 1)]
    max_fatigue = mst_kruskal(ascend_list, roots) ** 2

    res = max_fatigue - min_fatigue
    print(res)


def mst_kruskal(sorted_list: list, roots: list) -> int:
    total_cost = 0
    for weight, node_a, node_b in sorted_list:
        if find_root(node_a, roots) == find_root(node_b, roots):
            continue

        if weight == 0:
            total_cost += 1
        union(node_a, node_b, roots)
    return total_cost


def find_root(x: int, roots: list) -> int:
    if roots[x] == x:
        return x

    roots[x] = find_root(roots[x], roots)
    return roots[x]


def union(a: int, b: int, roots: list):
    root_a = find_root(a, roots)
    root_b = find_root(b, roots)

    if root_a > root_b:
        root_a, root_b = root_b, root_a

    roots[root_b] = root_a


if __name__ == '__main__':
    main()
