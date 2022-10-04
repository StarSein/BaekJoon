"""
1. 분리 집합으로 연결 요소 구분
2. 각 연결 요소 안에서 dfs의 시작점을 다르게 두어 최대 의사 전달 시간의 최솟값 찾기
"""
from sys import stdin
from collections import defaultdict, deque
from typing import List, Tuple, Any, Generator

input = lambda: stdin.readline().rstrip()


def solution(N: int, M: int, rel_list: List[Tuple[int, int]]):
    roots = [i for i in range(N + 1)]

    def find_root(node: int):
        if roots[node] != node:
            roots[node] = find_root(roots[node])

        return roots[node]

    def union(ra: int, rb: int):
        if ra > rb:
            ra, rb = rb, ra
        roots[rb] = ra

    for nodeA, nodeB in rel_list:
        rootA = find_root(nodeA)
        rootB = find_root(nodeB)
        if rootA != rootB:
            union(rootA, rootB)

    graph = [[] for _ in range(N + 1)]
    for nodeA, nodeB in rel_list:
        graph[nodeA].append(nodeB)
        graph[nodeB].append(nodeA)

    groups = defaultdict(list)
    gen: Generator[int, Any, None] = (find_root(x) for x in range(1, N + 1))
    for node, root in enumerate(gen, start=1):
        groups[root].append(node)

    def bfs(start_node: int) -> int:
        visit_set = set()
        dq = deque()
        dq.append(start_node)
        visit_set.add(start_node)
        dist = 0
        while dq:
            cur_size = len(dq)
            for i in range(cur_size):
                cur_node = dq.popleft()
                for next_node in graph[cur_node]:
                    if next_node not in visit_set:
                        dq.append(next_node)
                        visit_set.add(next_node)
            dist += 1
        return dist

    MAX_DIST = 99
    best_persons = defaultdict(lambda: [0, MAX_DIST + 1])
    for root, group in groups.items():
        for person in group:
            cur_dist = bfs(person)
            if cur_dist < best_persons[root][1]:
                best_persons[root][0] = person
                best_persons[root][1] = cur_dist

    print(len(groups))
    print(*sorted(x[0] for x in best_persons.values()), sep='\n')


if __name__ == '__main__':
    N = int(input())
    M = int(input())
    rel_list = [tuple(map(int, input().split())) for _ in range(M)]
    solution(N, M, rel_list)
