import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    maps = [list(input().split()) for col in range(n)]

    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]

    def bfs(c: int, r: int):
        maps[c][r] = cnt_node
        visited[c][r] = True
        for i in range(4):
            nc, nr = c + dy[i], r + dx[i]
            if 0 <= nc < n and 0 <= nr < m and maps[nc][nr] != '0' and not visited[nc][nr]:
                bfs(nc, nr)

    visited = [[False for row in range(m)] for col in range(n)]
    cnt_node = 1
    for col in range(n):
        for row in range(m):
            if maps[col][row] == '1':
                bfs(col, row)
                cnt_node += 1
    
    INF = 100
    min_dist = [[INF for row in range(cnt_node)] for col in range(cnt_node)]

    def dfs(c: int, r: int, start: int, dist: int, dir: int):
        if maps[c][r] == start:
            return
        elif maps[c][r] != '0':
            end = maps[c][r]
            if dist >= 3:
                min_dist[start][end] = min(min_dist[start][end], dist - 1)
            return

        nc, nr = c + dy[dir], r + dx[dir]
        if 0 <= nc < n and 0 <= nr < m:
            dfs(nc, nr, start, dist + 1, dir)

    for col in range(n):
        for row in range(m):
            if maps[col][row] != '0':
                for i in range(4):
                    nc, nr = col + dy[i], row + dx[i]
                    if 0 <= nc < n and 0 <= nr < m:
                        dfs(nc, nr, maps[col][row], 1, i)

    is_visited = [False] * cnt_node
    START = 1
    heap = [(0, START)]
    total_dist = 0
    while len(heap):
        curr_dist, curr_node = heapq.heappop(heap)
        if is_visited[curr_node]:
            continue
        total_dist += curr_dist
        is_visited[curr_node] = True

        for next_node in range(1, cnt_node):
            if not is_visited[next_node] and min_dist[curr_node][next_node] != INF:
                heapq.heappush(heap, (min_dist[curr_node][next_node], next_node))
    if sum(is_visited) == cnt_node - 1:
        print(total_dist)
    else:
        print(-1)


if __name__ == '__main__':
    main()
