from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M, K = map(int, input().split())
    balls = [tuple(map(int, input().split())) for _ in range(M)]
    return N, M, K, balls


def solution(N: int, M: int, K: int, balls: List[Tuple[int, int, int, int, int]]) -> int:
    cur_grid = [[[] for j in range(N)] for i in range(N)]
    nex_grid = [[[] for j in range(N)] for i in range(N)]

    dir_list = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)]

    for r, c, m, s, d in balls:
        cur_grid[r - 1][c - 1] = [(m, s, d)]

    for _ in range(K):
        for r in range(N):
            for c in range(N):
                for m, s, d in cur_grid[r][c]:
                    dr, dc = dir_list[d]
                    nr = (r + dr * s) % N
                    nc = (c + dc * s) % N
                    nex_grid[nr][nc].append((m, s, d))

        for r in range(N):
            for c in range(N):
                ball_cnt = len(nex_grid[r][c])
                if ball_cnt < 2:
                    cur_grid[r][c] = nex_grid[r][c][:]
                    nex_grid[r][c].clear()
                    continue

                m_sum = 0
                s_sum = 0
                all_odd = True
                all_even = True
                for m, s, d in nex_grid[r][c]:
                    m_sum += m
                    s_sum += s
                    if d % 2 == 1:
                        all_even = False
                    else:
                        all_odd = False

                nex_grid[r][c].clear()

                m_nex = m_sum // 5
                if m_nex == 0:
                    cur_grid[r][c].clear()
                else:
                    s_nex = s_sum // ball_cnt
                    dir_range = range(0, 8, 2) if all_odd or all_even else range(1, 8, 2)
                    cur_grid[r][c] = [(m_nex, s_nex, d_nex) for d_nex in dir_range]

    answer = 0
    for r in range(N):
        for c in range(N):
            answer += sum(m for m, s, d in cur_grid[r][c])
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
