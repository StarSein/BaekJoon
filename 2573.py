'''
각 칸을 하나의 정점으로 보고 동서남북을 서로 간선으로 연결하고,
어떤 정점의 값이 0이 되면 그 정점과 연결된 간선을 제거하는 방식으로 구현해볼까?
그런데 인접 배열로 구현하면 확인할 간선을 찾는 작업에서 시간 소모가 크다.
인접 리스트로 구현하면 제거할 간선을 찾는 작업에서 시간 소모가 크다.

그냥 2차원 배열에서 구현하자.
0이 되는 노드는 곧바로 처리하지 말고, 1년 단위로 일괄 처리 하자
어느 한 지점에서 bfs를 했을 때 방문하는 지점의 개수가 전체 빙산의 개수와 다를 경우
두 덩어리 이상으로 분리된 것이다.
'''

import sys
from collections import deque, defaultdict


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    board = [list(map(int, input().split())) for col in range(n)]
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    MELT = 0

    def cnt_coast(col: int, row: int) -> int:
        cnt = 0
        for i in range(4):
            nc, nr = col + dy[i], row + dx[i]
            if 0 <= nc < n and 0 <= nr < m:
                cnt += int(board[nc][nr] == MELT)
        return cnt

    def melt():
        cnt_melt = defaultdict(int)
        for col in range(n):
            for row in range(m):
                if board[col][row] != MELT:
                    cnt_melt[(col, row)] += cnt_coast(col, row)

        for key, cnt in cnt_melt.items():
            col, row = key
            board[col][row] = max(board[col][row] - cnt, MELT)

    def bfs(col: int, row: int) -> int:
        visit = [[False for r in range(m)] for c in range(n)]
        dq = deque([(col, row)])
        visit[col][row] = True
        num_visit = 0
        while dq:
            c, r = dq.popleft()
            num_visit += 1
            for i in range(4):
                nc, nr = c + dy[i], r + dx[i]
                if 0 <= nc < n and 0 <= nr < m and board[nc][nr] and not visit[nc][nr]:
                    dq.append((nc, nr))
                    visit[nc][nr] = True
        return num_visit

    def get_area_ice() -> int:
        area_ice = 0
        for col in range(n):
            for row in range(m):
                area_ice += int(board[col][row] != MELT)
        return area_ice

    def is_one_piece() -> bool:
        for col in range(n):
            for row in range(m):
                if board[col][row] != MELT:
                    return bfs(col, row) == get_area_ice()
        return False

    year = 0
    while is_one_piece():
        melt()
        year += 1

    answer = year if get_area_ice() != 0 else 0
    print(answer)


if __name__ == '__main__':
    main()
