import sys
from collections import deque
from copy import deepcopy


input = sys.stdin.readline
ROOT = 1

connect_table = dict()
child_parent = dict()
is_parent_set = set()


def solution():
    parent_queue = deque([ROOT])
    new_parent_queue = deque()
    while len(parent_queue) != 0:
        parent = parent_queue.popleft()
        is_parent_set.add(parent)
        for idx, child in enumerate(connect_table[parent]):
            if child not in is_parent_set:
                child_parent[child] = parent
                new_parent_queue.append(child)
        if len(parent_queue) == 0 and len(new_parent_queue) != 0:
            parent_queue = deepcopy(new_parent_queue)
            new_parent_queue.clear()
    sorted_child_parent = sorted(child_parent.items())

    for idx, val in enumerate(sorted_child_parent):
        print(val[1])


if __name__ == '__main__':
    n = int(input())
    for i in range(n-1):
        nodeA, nodeB = map(int, input().split())
        if nodeA not in connect_table:
            connect_table[nodeA] = [nodeB]
        else:
            connect_table[nodeA].append(nodeB)
        if nodeB not in connect_table:
            connect_table[nodeB] = [nodeA]
        else:
            connect_table[nodeB].append(nodeA)
    solution()
