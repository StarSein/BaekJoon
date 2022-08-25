import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    m, n = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(m)]
    visit = [[False for col in range(n)] for row in range(m)]

    dy = [0, 1, 0, -1]
    dx = [1, 0, -1, 0]

    if grid[0][0] == -1:
        return -1

    heap = []
    heapq.heappush(heap, (grid[0][0], 0, 0))
    while heap:
        cost, row, col = heapq.heappop(heap)

        if row == m - 1 and col == n - 1:
            return cost

        if visit[row][col]:
            continue

        visit[row][col] = True
        for i in range(4):
            nr = row + dy[i]
            nc = col + dx[i]
            if 0 <= nr < m and 0 <= nc < n and grid[nr][nc] != -1 and not visit[nr][nc]:
                heapq.heappush(heap, (cost + grid[nr][nc], nr, nc))
    return -1


if __name__ == '__main__':
    print(solution())
