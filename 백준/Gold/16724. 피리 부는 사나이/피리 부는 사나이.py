"""
1. 분리 집합으로 다 묶어준다.
2. 분리 집합의 개수를 센다.

try 1) WA.
원인: roots 배열을 갱신하지 않았음에도 find 메소드를 통하지 않고 
    직접 roots 배열을 통해 분리집합의 개수를 센 것
해결: union-find 를 한 다음, find 메소드를 통해 분리집합의 개수를 세기
"""
from sys import stdin
from typing import List


input = lambda: stdin.readline().rstrip()


def solution(N: int, M: int, grid: List[List[str]]) -> int:
    NUM_NODE = N * M

    dir_dict = {'R': (0, 1),
                'D': (1, 0),
                'L': (0, -1),
                'U': (-1, 0)}

    roots = [i for i in range(NUM_NODE)]

    def find_root(node: int) -> int:
        if roots[node] == node:
            return node
        else:
            roots[node] = find_root(roots[node])
            return roots[node]

    def union(rootA: int, rootB: int):
        if rootA > rootB:
            rootA, rootB = rootB, rootA

        roots[rootB] = rootA

    for r in range(N):
        for c in range(M):
            dr, dc = dir_dict[grid[r][c]]
            nr, nc = r + dr, c + dc
            cur_node = r * M + c
            next_node = nr * M + nc

            root_cur = find_root(cur_node)
            root_next = find_root(next_node)
            if root_cur != root_next:
                union(root_cur, root_next)

    component_cnt = len(set((find_root(node) for node in range(NUM_NODE))))
    return component_cnt


if __name__ == '__main__':
    _N, _M = map(int, input().split())
    _grid = [list(input()) for _ in range(_N)]
    print(solution(_N, _M, _grid))
