"""
1. 최소 신장 트리를 만든다. => 간선 리스트를 그대로 사용하기 위해 크루스칼 알고리즘을 사용하자.
2. 최소 신장 트리의 간선 중 가중치가 가장 높은 간선을 단절선으로 선택한다.
3. 최소 신장 트리의 길이에서 단절선의 길이를 뺀 것이 정답이다.
"""

from sys import stdin
from typing import List, Tuple

input = lambda: stdin.readline().rstrip()


def solution(n: int, m: int, edges: List[Tuple[int, int, int]]) -> int:
    def find_root(node: int) -> int:
        if roots[node] != node:
            roots[node] = find_root(roots[node])
        return roots[node]
    
    def union(ra: int, rb: int):
        if ra > rb:
            ra, rb = rb, ra
        roots[rb] = ra
        
    edges.sort(key=lambda x: x[2])

    MST_len = 0
    max_weight = 0
    roots = [i for i in range(n + 1)]
    for nodeA, nodeB, weight in edges:
        rootA = find_root(nodeA)
        rootB = find_root(nodeB)
        if rootA != rootB:
            union(rootA, rootB)
            MST_len += weight
            max_weight = max(max_weight, weight)

    ans = MST_len - max_weight
    return ans


if __name__ == '__main__':
    N, M = map(int, input().split())
    edge_list = [tuple(map(int, input().split())) for _ in range(M)]
    print(solution(N, M, edge_list))
