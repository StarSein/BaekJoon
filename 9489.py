import sys
from typing import List
from collections import deque

input = sys.stdin.readline


def solution(n: int, k: int, num_list: List[int]) -> int:
    node_info = dict()
    dq_parents = deque()
    tmp_parents = deque([num_list[0]])
    root_node = tmp_parents[0]
    node_info[root_node] = 0    # parent
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
            num_of_separate = len(tmp_parents)
            cnt_of_separate = 0
            dq_parents = tmp_parents

        if current_node != latest_node + 1:
            parent_node = dq_parents.popleft()

        node_info[current_node] = parent_node
        tmp_parents.append(current_node)

        latest_node = current_node
        i += 1

    cnt_of_sibling = 0
    target_parent = node_info[k]
    if target_parent == 0:
        return 0
    target_grandpa = node_info[target_parent]
    for key, val in node_info.items():
        if val == target_grandpa:
            for value in node_info.values():
                if value == key and value != target_parent:
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
