import sys
from itertools import combinations
from collections import deque


input = sys.stdin.readline


def solution() -> str:
    for node_a, node_b in combinations(truth_list, 2):  # 진실을 아는 사람들끼리 간선으로 묶는다
        connected[node_a].append(node_b)
        connected[node_b].append(node_a)

    for idx, party in enumerate(party_list):
        for node_a, node_b in combinations(party, 2):   # 같은 파티에 참석한 사람들끼리 간선으로 묶는다
            connected[node_a].append(node_b)
            connected[node_b].append(node_a)

    if num_truth == 0:      # 진실을 아는 사람의 수가 0이면, 모든 파티에서 과장할 수 있다
        return f'{m}'

    bfs(truth_list[0])      # 진실을 아는 한 사람의 관계망을 너비 우선 탐색하며 진실을 말해야 하는 사람을 판별한다

    cnt = 0
    for idx, party in enumerate(party_list):
        is_fun = True
        for i, node in enumerate(party):    # 진실을 아는 사람이 한 명이라도 있는 파티는 세지 않는다
            if node in truth_set:
                is_fun = False
                break
        if is_fun:                          # 진실을 아는 사람이 없는 파티는 센다
            cnt += 1

    return f'{cnt}'


def bfs(node: int):
    is_visited = [False] * (n + 1)
    visit_deque = deque([node])
    while len(visit_deque):
        current_node = visit_deque.popleft()
        if is_visited[current_node]:
            continue

        is_visited[current_node] = True

        for idx, next_node in enumerate(connected[current_node]):
            visit_deque.append(next_node)

    for node, is_visit in enumerate(is_visited):    # 방문한 모든 노드(사람)에게는 진실을 말해야 한다
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
