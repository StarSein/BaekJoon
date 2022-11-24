"""
모든 노드에 대해,
얼리 어답터가 아닌 자식 노드가 하나라도 있다면, 해당 노드가 얼리 어답터가 되어야 한다. - (1)

이 조건을 리프 노드로부터 상향식으로 적용해왔다면 '얼리 어답터가 아닌 자식 노드'의 모든 자식 노드는 얼리 어답터이다. - (2)
요구 사항의 충족을 위해 '얼리 어답터가 아닌 자식 노드'가 얼리 어답터가 되거나, 해당 노드가 얼리 어답터가 되어야 하는데,
진술 (2)에 의해 '얼리 어답터가 아닌 자식 노드'는 그 부모 노드만 얼리 어답터가 되면 요구 사항을 충족하게 되고,
자식 노드보다는 부모 노드가 얼리 어답터가 되는 것이 항상 최적이기 때문에, 진술 (1)이 요구 사항의 충분조건이 된다.

try 1) MLE
해결: tree를 따로 만들지 말고, 그래프에서 순회를 하자.
"""
from sys import stdin, setrecursionlimit
from typing import List, Tuple

setrecursionlimit(int(2e6))


def input():
    return stdin.readline().rstrip()


def make_graph(n: int, edges: List[Tuple[int, int]]) -> List[List[int]]:
    graph = [[] for _ in range(n + 1)]
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
    return graph


def solution(n: int, edges: List[Tuple[int, int]]) -> int:
    graph = make_graph(n, edges)
    ans = 0

    def dfs(cur: int, par: int) -> bool:
        nonlocal ans
        is_adapter = False
        for child in graph[cur]:
            if child != par and not dfs(child, cur):
                is_adapter = True
        if is_adapter:
            ans += 1
        return is_adapter

    dfs(1, 0)
    return ans


if __name__ == '__main__':
    N = int(input())
    edge_list = [tuple(map(int, input().split())) for _ in range(N - 1)]
    print(solution(N, edge_list))


