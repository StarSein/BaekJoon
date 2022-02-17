import sys
from itertools import combinations
from collections import deque


input = sys.stdin.readline


def solution() -> str:
    for node_a, node_b in combinations(truth_list, 2):
        connected[node_a].append(node_b)
        connected[node_b].append(node_a)

    for idx, party in enumerate(party_list):
        for node_a, node_b in combinations(party, 2):
            connected[node_a].append(node_b)
            connected[node_b].append(node_a)

    if num_truth == 0:
        return f'{m}'

    bfs(truth_list[0])

    cnt = 0
    for idx, party in enumerate(party_list):
        is_fun = True
        for i, node in enumerate(party):
            if node in truth_set:
                is_fun = False
                break
        if is_fun:
            cnt += 1

    return f'{cnt}'


def bfs(node: int):
    is_visited = [False] * (n + 1)
    visit_deque = deque([node])
    is_truth = False
    while len(visit_deque):
        current_node = visit_deque.popleft()
        if is_visited[current_node]:
            continue

        is_visited[current_node] = True

        if current_node in truth_set:
            is_truth = True

        for idx, next_node in enumerate(connected[current_node]):
            visit_deque.append(next_node)

    if is_truth:
        for node, is_visit in enumerate(is_visited):
            if is_visit:
                truth_set.add(node)


if __name__ == '__main__':
    n, m = map(int, input().split())
    inp = list(map(int, input().split()))
    num_truth = inp[0]
    truth_list = inp[1:]
    truth_set = set(truth_list)
    party_list = []
    for party in range(m):
        inp = list(map(int, input().split()))
        party_list.append(inp[1:])

    connected = [[] for node in range(n + 1)]
    print(solution())
