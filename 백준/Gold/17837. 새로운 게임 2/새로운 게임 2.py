from sys import stdin


input = lambda: stdin.readline().rstrip()


def solution() -> int:
    WALL, WHITE, RED, BLUE = -1, 0, 1, 2

    N, K = map(int, input().split())
    board = [[WALL] * (N + 2)]
    for _ in range(N):
        board.append([WALL] + list(map(int, input().split())) + [WALL])
    board.append([WALL] * (N + 2))
    piece_info_list = [list(map(int, input().split())) for _ in range(K)]

    grid = [[[] for c in range(N + 2)] for r in range(N + 2)]
    for i, (r, c, d) in enumerate(piece_info_list):
        grid[r][c].append(i)

    dr = [0, 0, 0, -1, 1]
    dc = [0, 1, -1, 0, 0]

    def get_reverse_dir(d: int) -> int:
        return 3 - d if d < 3 else 7 - d

    TURN_LIMIT = 1000
    turn_cnt = 0
    while turn_cnt < TURN_LIMIT:
        turn_cnt += 1

        for i in range(K):
            r, c, d = piece_info_list[i]
            idx = grid[r][c].index(i)
            moving_pieces = grid[r][c][idx:]

            nr, nc = r + dr[d], c + dc[d]
            if board[nr][nc] == WALL or board[nr][nc] == BLUE:
                d = get_reverse_dir(d)
                piece_info_list[i][2] = d
                nr, nc = r + dr[d], c + dc[d]
                if board[nr][nc] == WALL or board[nr][nc] == BLUE:
                    continue

            del grid[r][c][idx:]
            if board[nr][nc] == WHITE:
                grid[nr][nc].extend(moving_pieces)
            else:
                grid[nr][nc].extend(moving_pieces[::-1])
       
            for j in moving_pieces:
                piece_info_list[j][0] = nr
                piece_info_list[j][1] = nc

            if len(grid[nr][nc]) >= 4:
                return turn_cnt

    return -1


if __name__ == '__main__':
    print(solution())
