import sys
from typing import List
from collections import deque

input = sys.stdin.readline
DEPTH_IDX = 0
PARENT_IDX = 1


def solution(n: int, k: int, num_list: List[int]) -> int:
    node_info = dict()
    dq_parents = deque()
    tmp_parents = deque([num_list[0]])
    root_node = tmp_parents[0]
    depth = 1
    node_info[root_node] = (depth, 0)    # (depth, parent)
    num_of_separate = 1
    cnt_of_separate = 0
    latest_node = root_node
    parent_node = 0
    i = 1
    while i < n:
        current_node = num_list[i]
        if current_node != latest_node + 1:
            cnt_of_separate += 1

        if cnt_of_separate == num_of_separate:
            depth += 1
            num_of_separate = len(tmp_parents)
            cnt_of_separate = 0
            dq_parents = tmp_parents

        if current_node != latest_node + 1:
            parent_node = dq_parents.popleft()

        node_info[current_node] = (depth, parent_node)
        tmp_parents.append(current_node)

        latest_node = current_node
        i += 1

    print(node_info)

    cnt_of_sibling = 0
    target_depth = node_info[k][DEPTH_IDX]
    target_parent = node_info[k][PARENT_IDX]
    for value in node_info.values():
        if value[DEPTH_IDX] == target_depth and value[PARENT_IDX] != target_parent:
            cnt_of_sibling += 1
    return cnt_of_sibling


if __name__ == '__main__':
    while True:
        n, k = map(int, input().split())
        if n == 0:
            break

        num_list = list(map(int, input().split()))
        sol = solution(n, k, num_list)
        print(sol)