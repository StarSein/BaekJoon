from sys import stdin
from collections import deque
from typing import Tuple


input = lambda: stdin.readline().rstrip()


def solution() -> int:
    N, M, fuel = map(int, input().split())
    grid = tuple(list(map(lambda x: -int(x), input().split())) for _ in range(N))
    init_row, init_col = map(lambda x: int(x) - 1, input().split())
    client_tuple = tuple(tuple(map(lambda x: int(x) - 1, input().split())) for _ in range(M))

    EMPTY = 0
    WALL = -1

    destinations = [(-1, -1)] * (M + 1)
    for i, (sr, sc, er, ec) in enumerate(client_tuple, start=1):
        grid[sr][sc] = i
        destinations[i] = (er, ec)

    def choose_client(r: int, c: int) -> Tuple[int, int, int]:
        dir_tuple = ((0, 1), (1, 0), (0, -1), (-1, 0))
        visited = tuple([False] * N for _ in range(N))

        cdd_list = []
        INF = 400
        min_dist = INF
        dist = 0
        dq = deque()
        dq.append((r, c))
        visited[r][c] = True
        while dq and min_dist == INF:
            for _ in range(len(dq)):
                cr, cc = dq.popleft()

                if grid[cr][cc] > 0:
                    min_dist = dist
                    cdd_list.append((cr, cc))
                    continue

                for dr, dc in dir_tuple:
                    nr, nc = cr + dr, cc + dc
                    if 0 <= nr < N and 0 <= nc < N and grid[nr][nc] != WALL and not visited[nr][nc]:
                        dq.append((nr, nc))
                        visited[nr][nc] = True
            dist += 1
        if min_dist != INF:
            return min_dist, *min(cdd_list)
        else:
            return -1, -1, -1

    def take_client(sr: int, sc: int, er: int, ec: int) -> int:
        dir_tuple = ((0, 1), (1, 0), (0, -1), (-1, 0))
        visited = tuple([False] * N for _ in range(N))

        dist = 0
        dq = deque()
        dq.append((sr, sc))
        visited[sr][sc] = True
        while dq:
            dq_size = len(dq)
            for _ in range(dq_size):
                cr, cc = dq.popleft()
                if cr == er and cc == ec:
                    return dist

                for dr, dc in dir_tuple:
                    nr, nc = cr + dr, cc + dc
                    if 0 <= nr < N and 0 <= nc < N and grid[nr][nc] != WALL and not visited[nr][nc]:
                        dq.append((nr, nc))
                        visited[nr][nc] = True
            dist += 1
        return -1

    client_cnt = M
    row, col = init_row, init_col
    while client_cnt:
        client_cnt -= 1

        cost1, row, col = choose_client(row, col)
        if cost1 == -1 or cost1 > fuel:
            return -1
        fuel -= cost1
        client = grid[row][col]
        grid[row][col] = EMPTY

        cost2 = take_client(row, col, *destinations[client])
        if cost2 == -1 or cost2 > fuel:
            return -1
        fuel += cost2
        row, col = destinations[client]

    return fuel


if __name__ == '__main__':
    print(solution())
