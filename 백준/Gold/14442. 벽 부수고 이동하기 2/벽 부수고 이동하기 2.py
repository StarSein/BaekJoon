"""
백트래킹으로 접근해야겠다.
시간 메모리 효율성을 위해 DP 메모이제이션을 사용하자.
bfs를 하면서 벽을 부순 횟수가 k를 초과할 경우 해당 탐색 경로의 확장을 중단한다.

[pypy3 수 차례의 메모리 초과]
visit에 처음 도착했을 때가 항상 최단 경로다.
따라서 최솟값 갱신을 할 필요 없이, 중복 방문만 제외해주면 된다.
"""
import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m, k = map(int, input().split())
    matrix = [[int(x) for x in input()] for col in range(n)]
    visit = [[0 for row in range(m)] for col in range(n)]

    delta_pos = (0, 1), (0, -1), (1, 0), (-1, 0)

    def bfs() -> int:
        new_visit_dq = deque([(0, 0, 0)])
        cnt_move = 1
        visit[0][0] = ~visit[0][0]
        while new_visit_dq:
            visit_dq = new_visit_dq
            new_visit_dq = deque()
            while visit_dq:
                col, row, cnt_wall = visit_dq.popleft()

                if col == n - 1 and row == m - 1:
                    return cnt_move

                for dy, dx in delta_pos:
                    nc, nr = col + dy, row + dx
                    if 0 <= nc < n and 0 <= nr < m:
                        new_cnt_wall = cnt_wall + 1 if matrix[nc][nr] else cnt_wall
                        if new_cnt_wall <= k and ~visit[nc][nr] & 1 << new_cnt_wall:
                            new_visit_dq.append((nc, nr, new_cnt_wall))
                            visit[nc][nr] = ~((1 << new_cnt_wall) - 1)

            cnt_move += 1
        return -1

    print(bfs())


if __name__ == '__main__':
    main()
