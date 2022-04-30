"""
만들 수 있는 미로의 개수 4^5 = 1024
미로 찾기 작업에서 방문 지점의 최대 개수 = 5^3 = 125
가능한 시작 지점(꼭짓점)의 개수 7개
판을 쌓는 경우의 수 5! = 120
bfs를 이용해 거리를 구하고, 최단 거리를 완전 탐색하자.
"""
import sys
from copy import deepcopy
from collections import deque
from itertools import product, permutations


def input():
    return sys.stdin.readline().rstrip()


def main():
    def rotate(_level: int):
        board = maze[_level]
        new_board = [[-1 for row in range(5)] for col in range(5)]
        for col in range(5):
            for row in range(5):
                new_board[col][row] = board[4-row][col]
        maze[_level] = new_board

    def bfs(xs: int, ys: int, zs: int) -> int:
        xe, ye, ze = 4 - xs, 4 - ys, 4 - zs
        if maze[xs][ys][zs] == 0 or maze[xe][ye][ze] == 0:
            return INF
        end_point = (xe, ye, ze)
        dq = deque([(0, xs, ys, zs)])
        visited_set = set()
        while dq:
            dist, x, y, z = dq.popleft()
            tup_xyz = (x, y, z)
            if tup_xyz in visited_set:
                continue
            if tup_xyz == end_point:
                return dist
            visited_set.add(tup_xyz)
            for i in range(6):
                nx, ny, nz = x + dx[i], y + dy[i], z + dz[i]
                if 0 <= nx < 5 and 0 <= ny < 5 and 0 <= nz < 5 and maze[nx][ny][nz]:
                    dq.append((dist + 1, nx, ny, nz))
        return INF

    def get_min_dist(level: int) -> int:
        if level == 5:
            maze_str = str(maze)
            if maze_str not in checked_maze_set:
                checked_maze_set.add(maze_str)
                return min(bfs(x, y, z) for x, y, z in vertex_list)
            else:
                return INF

        min_dist = INF
        for i in range(4):
            rotate(level)
            min_dist = min(min_dist, get_min_dist(level + 1))
        return min_dist

    board_list = [[list(map(int, input().split())) for _ in range(5)] for _ in range(5)]
    INF = 125
    vertex_list = list(product(*[[0, 4], [0, 4], [0, 4]]))
    dx = [1, -1, 0, 0, 0, 0]
    dy = [0, 0, 1, -1, 0, 0]
    dz = [0, 0, 0, 0, 1, -1]
    min_dist = INF
    checked_maze_set = set()
    for board_order in permutations(range(5), 5):
        maze = [deepcopy(board_list[level]) for level in board_order]
        min_dist = min(min_dist, get_min_dist(0))
    print(min_dist if min_dist != INF else -1)


if __name__ == '__main__':
    main()
