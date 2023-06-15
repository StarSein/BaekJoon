from sys import stdin, setrecursionlimit
from typing import List, Tuple

setrecursionlimit(int(1e6))


def input():
    return stdin.readline().rstrip()


def read_input():
    N, W = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(N - 1)]
    return N, W, edges


def solution(N: int, W: int, edges: List[Tuple[int, int]]) -> float:

    def dfs(cur: int, par: int) -> int:
        ret = 0
        child_cnt = 0
        for nex in graph[cur]:
            if nex != par:
                child_cnt += 1
                ret += dfs(nex, cur)
        return ret if child_cnt else 1

    graph = [[] for _ in range(N + 1)]
    for a, b in edges:
        graph[a].append(b)
        graph[b].append(a)

    leaf_cnt = dfs(1, 0)
    answer = W / leaf_cnt
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
