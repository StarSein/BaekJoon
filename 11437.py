import sys
from collections import deque
from copy import deepcopy

input = sys.stdin.readline
ROOT = 1


def make_tree_calc_depths():
    node_queue = deque([ROOT])
    visited_set = set()
    depth = 1
    while len(visited_set) < n:
        next_queue = deque()
        while len(node_queue):
            current = node_queue.popleft()
            depth_list[current] = depth
            visited_set.add(current)
            for idx, val in enumerate(connect_list[current]):
                if val not in visited_set:
                    next_queue.append(val)
                    parent_list[val] = current
        node_queue = deepcopy(next_queue)
        next_queue.clear()
        depth += 1


def find_co_ancestor(nodeA: int, nodeB: int):
    depthA = depth_list[nodeA]
    depthB = depth_list[nodeB]
    if depthA < depthB:
        while depthA < depthB:
            nodeB = parent_list[nodeB]
            depthB -= 1
    elif depthA > depthB:
        while depthA > depthB:
            nodeA = parent_list[nodeA]
            depthA -= 1
    else:
        pass

    while nodeA != nodeB:
        nodeA = parent_list[nodeA]
        nodeB = parent_list[nodeB]
    return nodeA


if __name__ == '__main__':
    n = int(input())
    connect_list = [[] for _ in range(n+1)]
    for i in range(n-1):
        nodeA, nodeB = map(int, input().split())
        connect_list[nodeA].append(nodeB)
        connect_list[nodeB].append(nodeA)
    parent_list = [0] * (n+1)
    depth_list = [0] * (n+1)
    make_tree_calc_depths()
    m = int(input())
    for j in range(m):
        nodeA, nodeB = map(int, input().split())
        co_ancestor = find_co_ancestor(nodeA, nodeB)
        print(co_ancestor)
