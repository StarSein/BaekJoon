from sys import stdin, setrecursionlimit
from math import ceil
from typing import List, Tuple


setrecursionlimit(int(1e6))


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N, T = map(int, input().split())
    edge_list = [tuple(map(int, input().split())) for _ in range(N - 1)]
    return N, T, edge_list


def solution(N: int, T: int, edge_list: List[Tuple[int, int, int]]) -> int:
    def dfs(cur: int, par: int) -> Tuple[int, int]:
        nonlocal best_tup
        ret = (1, 0)
        child_results = []
        for nex, weight in graph[cur]:
            if nex != par:
                chl_num, chl_time = dfs(nex, cur)
                child_results.append((chl_num + 1, chl_time + weight))
        if len(child_results) > 1:
            child_results.sort(reverse=True, key=my_key)
            best_tup = max(best_tup, (child_results[0][0] + child_results[1][0], child_results[0][1] + child_results[1][1]),
                           key=my_key)
        if child_results:
            ret = max(ret, child_results[0], key=my_key)
        return ret

    my_key = lambda x: (x[0], -x[1])
    ROOT = 1
    graph = [[] for _ in range(N + 1)]
    for a, b, c in edge_list:
        graph[a].append((b, c))
        graph[b].append((a, c))
    best_tup = (0, 0)
    root_tup = dfs(ROOT, 0)
    best_tup = max(best_tup, root_tup, key=my_key)
    return ceil(best_tup[1] / T)


if __name__ == '__main__':
    print(solution(*read_test_case()))
