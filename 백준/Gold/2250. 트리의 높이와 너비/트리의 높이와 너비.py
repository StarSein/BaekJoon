"""
주어진 이진트리를 중위순회하면서 노드마다 인덱스값을 부여하자.
이때 depth별 노드 리스트에 방문한 노드의 인덱스값을 저장하자.

[1차 채점] WA.
루트 노드가 1이라는 보장은 없다. 지문을 성실히 읽지 않았다.

[2차 채점] WA.
트리의 노드가 번호 순서대로 입력된다는 보장이 없다. 지문을 너무 급하게 읽는다.
"""
import sys
from collections import defaultdict


sys.setrecursionlimit(int(2e5))


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    is_not_child_set = set(range(1, n + 1))
    tree = [[] for node in range(n + 1)]
    for node in range(n):
        num, left, right = map(int, input().split())
        tree[num] = [left, right]
        is_not_child_set.discard(left)
        is_not_child_set.discard(right)
    root = is_not_child_set.pop()
    LEFT, RIGHT = 0, 1
    grid = []

    def store_node(node: int, depth: int):
        if tree[node][LEFT] != -1:
            store_node(tree[node][LEFT], depth + 1)
        grid.append(depth)
        if tree[node][RIGHT] != -1:
            store_node(tree[node][RIGHT], depth + 1)

    store_node(root, 1)
    depth_indices = defaultdict(list)
    for idx, depth in enumerate(grid, start=1):
        depth_indices[depth].append(idx)

    best_depth, best_width = 0, -1
    for depth, indices in sorted(depth_indices.items()):
        width = indices[-1] - indices[0] + 1
        if width > best_width:
            best_width = width
            best_depth = depth

    print(best_depth, best_width)


if __name__ == '__main__':
    main()
