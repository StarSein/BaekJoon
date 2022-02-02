import sys


sys.setrecursionlimit(250_000)

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

    global lineup
    lineup = []
    find_optimal_lineup(root_node, True)
    lineup.sort()
    lineup.append(-1)
    for idx, val in enumerate(lineup):
        print(val, end=' ')
    print()

    lineup = []
    find_optimal_lineup(root_node, False)
    lineup.sort()
    lineup.append(-1)
    for idx, val in enumerate(lineup):
        print(val, end=' ')
    print()


def make_dp(node: int):
    num_child = len(childs[node])
    dp[node][False] = [DEFAULT_SCORE, [False] * num_child]
    dp[node][True] = [scores[node-1], [False] * num_child]
    for i in range(num_child):
        child = childs[node][i]
        make_dp(child)

        is_child_in = (dp[child][True][OPTIMAL_SCORE] > dp[child][False][OPTIMAL_SCORE])
        dp[node][False][OPTIMAL_SCORE] += dp[child][is_child_in][OPTIMAL_SCORE]
        dp[node][False][IS_CHILD_IN][i] = is_child_in

        dp[node][True][OPTIMAL_SCORE] += dp[child][False][OPTIMAL_SCORE]


def find_optimal_lineup(current_node: int, is_current_in: bool):
    global lineup

    if is_current_in:
        lineup.append(current_node)

    for idx, child in enumerate(childs[current_node]):
        is_child_in = dp[current_node][is_current_in][IS_CHILD_IN][idx]
        find_optimal_lineup(child, is_child_in)


if __name__ == '__main__':
    n = int(input())
    scores = list(map(int, input().split()))
    parents = list(map(int, input().split()))
    childs = [[] for node in range(n + 1)]
    for idx, val in enumerate(parents, start=2):
        childs[val].append(idx)
    dp = [[0, []] for col in range(n + 1)]
    solution()
