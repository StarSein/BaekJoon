"""
미로 안에서 s와 k들을 연결하는 최소 신장 트리를 찾는 문제.
bfs로 미리 각 지점들 사이의 거리를 구해놓자.
"""
import sys
from collections import deque
from typing import Tuple


def input():
    return sys.stdin.readline().rstrip()


def main():
    dy = 0, 1, 0, -1
    dx = 1, 0, -1, 0

    def find_edge(_col: int, _row: int):
        cnt_move = 0
        visit_dq = deque([(_col, _row)])
        visited = [[False for r in range(n)] for c in range(n)]
        visited[_col][_row] = True
        while visit_dq:
            next_dq = deque()
            while visit_dq:
                c, r = visit_dq.popleft()

                if maze[c][r].isalpha():
                    edges.append((_col, _row, c, r, cnt_move))
                for i in range(4):
                    nc = c + dy[i]
                    nr = r + dx[i]
                    if 0 <= nc < n and 0 <= nr < n and maze[nc][nr] != '1' and not visited[nc][nr]:
                        next_dq.append((nc, nr))
                        visited[nc][nr] = True
            visit_dq = next_dq
            cnt_move += 1

    n, m = map(int, input().split())
    maze = [input() for col in range(n)]
    edges = []
    roots = dict()
    for col in range(n):
        for row in range(n):
            if maze[col][row].isalpha():
                find_edge(col, row)
                roots[(col, row)] = (col, row)

    def find_root(x: Tuple[int, int]) -> Tuple[int, int]:
        if roots[x] == x:
            return x
        roots[x] = find_root(roots[x])
        return roots[x]

    def union(a: Tuple[int, int], b: Tuple[int, int]):
        if a > b:
            a, b = b, a
        roots[b] = a

    total_move = 0
    for ca, ra, cb, rb, dist in sorted(edges, key=lambda x: x[4]):
        root_a = find_root((ca, ra))
        root_b = find_root((cb, rb))
        if root_a != root_b:
            union(root_a, root_b)
            total_move += dist

    print(total_move if len(set([find_root(x) for x in roots.keys()])) == 1 else -1)


if __name__ == '__main__':
    main()
