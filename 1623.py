import sys
from collections import deque


input = sys.stdin.readline
root_node = 1
DEFAULT_SCORE = 0
END_OF_TREE = 0

# indices in dp[node][is_node_in][]
OPTIMAL_SCORE = 0
IS_CHILD_IN = 1


def solution():
    make_dp(root_node)
    print(dp[root_node][True][OPTIMAL_SCORE], dp[root_node][False][OPTIMAL_SCORE])

    lineup = find_optimal_lineup(root_node, True)
    lineup.sort()
    lineup.append(-1)
    for idx, val in enumerate(lineup):
        print(val, end=' ')
    print()

    lineup = find_optimal_lineup(root_node, False)
    lineup.sort()
    lineup.append(-1)
    for idx, val in enumerate(lineup):
        print(val, end=' ')
    print()


def make_dp(start_node: int):
    st_make = []
    st_visit = [start_node]
    while len(st_make) < n:
        next_visit = []
        while len(st_visit):
            current_node = st_visit.pop()
            for i in range(len(childs[current_node])):
                next_visit.append(childs[current_node][i])
            st_make.append(current_node)
        st_visit = next_visit[:]
        
    while len(st_make):
        node = st_make.pop()
        num_child = len(childs[node])
        dp[node][False] = [DEFAULT_SCORE, [False] * num_child]
        dp[node][True] = [scores[node-1], [False] * num_child]
        for i in range(num_child):
            child = childs[node][i]

            is_child_in = (dp[child][True][OPTIMAL_SCORE] > dp[child][False][OPTIMAL_SCORE])
            dp[node][False][OPTIMAL_SCORE] += dp[child][is_child_in][OPTIMAL_SCORE]
            dp[node][False][IS_CHILD_IN][i] = is_child_in

            dp[node][True][OPTIMAL_SCORE] += dp[child][False][OPTIMAL_SCORE]


def find_optimal_lineup(start_node: int, is_start_in: bool):
    lineup = []
    q_visit = deque([(start_node, is_start_in)])
    while len(q_visit):
        current_node, is_current_in = q_visit.popleft()
        if is_current_in:
            lineup.append(current_node)
        for i in range(len(childs[current_node])):
            is_child_in = dp[current_node][is_current_in][IS_CHILD_IN][i]
            q_visit.append((childs[current_node][i], is_child_in))

    return lineup


if __name__ == '__main__':
    n = int(input())
    scores = list(map(int, input().split()))
    parents = list(map(int, input().split()))
    childs = [[] for node in range(n + 1)]
    for idx, val in enumerate(parents, start=2):
        childs[val].append(idx)
    dp = [[[], []] for col in range(n + 1)]
    solution()
