import sys
from collections import deque
from copy import deepcopy


input = sys.stdin.readline
ROOT = 1

connect_table = dict()   # key: 특정 노드, value: 해당 노드와 연결된 모든 노드
child_parent = dict()    # key: 자식 노드, value: 부모 노드
is_parent_set = set()    # bfs의 is_visited와 같은 역할
												 # -> 노드 번호에 관한 정보가 없어서 리스트를 안 쓰고 집합 사용

def solution():
    parent_queue = deque([ROOT])
    while len(parent_queue) != 0:
        parent = parent_queue.popleft()
        is_parent_set.add(parent)
        for idx, child in enumerate(connect_table[parent]):
            if child not in is_parent_set:
                child_parent[child] = parent
                parent_queue.append(child)

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
