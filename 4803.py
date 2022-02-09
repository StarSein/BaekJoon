import sys


input = sys.stdin.readline


def find_root(x: int) -> int:
    if roots[x] == x:
        return x

    roots[x] = find_root(roots[x])
    return roots[x]


def union(a_root: int, b_root: int):
    high_root = max(a_root, b_root)
    low_root = min(a_root, b_root)
    roots[high_root] = low_root

    if high_root in tree_set and low_root in tree_set:
        tree_set.discard(high_root)
    elif high_root in tree_set:
        tree_set.discard(high_root)
    elif low_root in tree_set:
        tree_set.discard(low_root)
    else:
        pass


if __name__ == '__main__':
    cnt_case = 1
    while True:
        n, m = map(int, input().split())
        if n == 0 and m == 0:
            break

        roots = [node for node in range(n + 1)]
        tree_set = set(roots[1:])
        for edge in range(m):
            node_a, node_b = map(int, input().split())

            a_root = find_root(node_a)
            b_root = find_root(node_b)

            if a_root != b_root:
                union(a_root, b_root)
            else:
                tree_set.discard(a_root)

        num_tree = len(tree_set)
        if num_tree == 0:
            print(f"Case {cnt_case}: No trees.")
        elif num_tree == 1:
            print(f"Case {cnt_case}: There is one tree.")
        else:
            print(f"Case {cnt_case}: A forest of {num_tree} trees.")
        cnt_case += 1
