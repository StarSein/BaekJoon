import sys
from collections import deque
from typing import List


input = sys.stdin.readline
ROOT = 1    # root node는 어떤 노드가 되어도 상관없다


def solution():
    st_visit = make_tree(ROOT)

    while len(st_visit):
        current = st_visit.pop()
        if len(childs[current]) == 0:   # leaf node는 건너뛴다
            continue

        num_early_child = 0
        num_early_descend = 0
        for idx, child in enumerate(childs[current]):
            if dp[child][0]:
                num_early_child += 1
            num_early_descend += dp[child][1]
        if num_early_child == len(childs[current]):
            dp[current] = (False, num_early_descend)
        else:
            dp[current] = (True, num_early_descend + 1)
    sol = dp[ROOT][1]
    print(sol)


def make_tree(start: int) -> List[int]:
    stack = []
    q_visit = deque([start])
    is_visit = [False] * (n + 1)
    while len(q_visit):
        current = q_visit.popleft()
        stack.append(current)
        is_visit[current] = True
        for idx, adjacent in enumerate(connected[current]):
            if not is_visit[adjacent]:
                childs[current].append(adjacent)
                q_visit.append(adjacent)
    return stack


if __name__ == '__main__':
    n = int(input())
    connected = [[] for node in range(n + 1)]
    for edge in range(n - 1):
        u, v = map(int, input().split())
        connected[u].append(v)
        connected[v].append(u)
    childs = [[] for node in range(n + 1)]
    dp = [(False, 0)] * (n + 1)
    solution()
