import sys
import heapq
from collections import defaultdict


def input():
    return sys.stdin.readline().rstrip()


def main():
    xs, ys = map(int, input().split())
    xe, ye = map(int, input().split())
    teleports = dict()
    for teleport in range(3):
        x1, y1, x2, y2 = map(int, input().split())
        teleports[(x1, y1)] = (x2, y2)
        teleports[(x2, y2)] = (x1, y1)

    def dijkstra(xs, ys, xe, ye) -> int:
        dx = [0, 1, 0, -1]
        dy = [1, 0, -1, 0]
        heap = [(0, xs, ys)]
        is_visited = defaultdict(bool)
        while heap:
            curr_time, cx, cy = heapq.heappop(heap)
            if is_visited[(cx, cy)]:
                continue

            if cx == xe and cy == ye:
                return curr_time

            is_visited[(cx, cy)] = True
            for i in range(4):
                nx, ny = cx + dx[i], cy + dy[i]
                if nx >= 0 and ny >= 0:
                    heapq.heappush(heap, (curr_time + 1, nx, ny))
            if (cx, cy) in teleports:
                nx, ny = teleports[(cx, cy)]
                heapq.heappush(heap, (curr_time + 10, nx, ny))

    print(dijkstra(xs, ys, xe, ye))


if __name__ == '__main__':
    main()
