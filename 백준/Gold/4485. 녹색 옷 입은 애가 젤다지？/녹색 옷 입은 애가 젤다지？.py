"""
상하좌우 이동 가능하므로 Bottom-up 방식의 동적계획법으로 풀자.

[1차 채점] TLE
다익스트라로 최단 경로만 찾아야 통과할 수 있나 보다.
"""
import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def main():
    cnt_tc = 1
    while True:
        n = int(input())
        if n == 0:
            break
        cave = [list(map(int, input().split())) for _ in range(n)]
        heap = [(cave[0][0], 0, 0)]

        y_steps = [0, 1, 0, -1]
        x_steps = [1, 0, -1, 0]
        visit = [[False for row in range(n)] for col in range(n)]
        while heap:
            loss, col, row = heapq.heappop(heap)
            if visit[col][row]:
                continue
            visit[col][row] = True
            if col == n - 1 and row == n - 1:
                print(f"Problem {cnt_tc}: {loss}")
                cnt_tc += 1
                break
            for dy, dx in zip(y_steps, x_steps):
                nc, nr = col + dy, row + dx
                if 0 <= nc < n and 0 <= nr < n:
                    heapq.heappush(heap, (loss + cave[nc][nr], nc, nr))


if __name__ == '__main__':
    main()
