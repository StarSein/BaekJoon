import sys
from collections import deque

sys.setrecursionlimit(6)

input = sys.stdin.readline


def solution():
    make_tree(r, -1, 2)
    cnt_nodes_of_subtree(r)
    while len(q_query):
        query = q_query.popleft()
        print(cnt_nos[query])


def make_tree(current: int, parent: int, depth: int):
    for idx, adjacent in enumerate(connected[current]):
        if adjacent != parent:
            childs[current].append(adjacent)
            make_tree(adjacent, current, depth + 1)


def cnt_nodes_of_subtree(current: int):
    cnt_nos[current] = 1
    for idx, child in enumerate(childs[current]):
        cnt_nos[current] += cnt_nodes_of_subtree(child)
    return cnt_nos[current]


if __name__ == '__main__':
    n, r, q = map(int, input().split())
    connected = [[] for node in range(n+1)]
    for edge in range(n-1):
        u, v = map(int, input().split())
        connected[u].append(v)
        connected[v].append(u)
    q_query = deque([int(input()) for query in range(q)])
    childs = [[] for node in range(n+1)]
    cnt_nos = [0] * (n+1)
    solution()
