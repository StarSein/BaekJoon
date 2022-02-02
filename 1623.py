import sys
from collections import deque
from copy import deepcopy
from typing import List


input = sys.stdin.readline
ROOT = 1
INIT_SCORE = 0


def solution():
    make_dp(ROOT)
    print(dp[ROOT][1], dp[ROOT][0])

    lineup = find_opt_lineup(ROOT, True)
    for idx, val in enumerate(lineup):
        print(val, end=' ')
    print()

    lineup = find_opt_lineup(ROOT, False)
    for idx, val in enumerate(lineup):
        print(val, end=' ')
    print()


def make_dp(start: int):
    st_make = []
    st_visit = [start]
    while len(st_make) < n:
        next_visit = []
        while len(st_visit):
            current = st_visit.pop()
            st_make.append(current)
            for i in range(len(childs[current])):
                next_visit.append(childs[current][i])
        st_visit = deepcopy(next_visit)

    while len(st_make):
        node = st_make.pop()
        dp[node][0] = INIT_SCORE
        dp[node][1] = scores[node-1]
        for i in range(len(childs[node])):
            child = childs[node][i]
            dp[node][0] += max(dp[child][0], dp[child][1])
            dp[node][1] += dp[child][0]


def find_opt_lineup(start: int, is_start_in: bool) -> List[int]:
    lineup = []
    q_visit = deque([(start, is_start_in)])
    while len(q_visit):
        current, is_current_in = q_visit.popleft()
        if is_current_in:
            lineup.append(current)
            for i in range(len(childs[current])):
                child = childs[current][i]
                is_child_in = False
                q_visit.append((child, is_child_in))
        else:
            for i in range(len(childs[current])):
                child = childs[current][i]
                is_child_in = (dp[child][1] > dp[child][0])
                q_visit.append((child, is_child_in))
    lineup.sort()
    lineup.append(-1)
    return lineup


if __name__ == '__main__':
    n = int(input())
    scores = list(map(int, input().split()))
    parents = list(map(int, input().split()))
    childs = [[] for node in range(n + 1)]
    for idx, val in enumerate(parents, start=2):
        childs[val].append(idx)
    dp = [[0, 0] for node in range(n + 1)]
    solution()
