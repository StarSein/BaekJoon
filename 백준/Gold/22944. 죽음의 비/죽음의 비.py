"""
N, H, D의 크기를 볼 때 메모이제이션을 하기는 어렵다.
좌표평면에서 bfs를 할 때 중복 방문을 어떻게 배제해야 할 지 모르겠다.
그래프의 관점으로 접근하면 좀 나을 것이다.
노드의 개수는 총 K + 2 ( K <= 10 ) 이므로,
노드 간의 맨해튼 거리를 구하고 다익스트라를 사용하는데, S에서 출발할 때만 거리에 H를 상한선으로 두고,
U에서 출발할 때는 거리에 D를 상한선으로 두면 된다.
[1차 채점] WA.
S에서 출발해서 우산에 도착하고 남은 H를 다시 사용할 수 있다는 점을 간과했다.
[2차 채점] WA.
이미 방문한 지점을 방문할 수 있어야 한다.
단, 쓸데없는 이동으로 인한 무한 루프는 예방해줘야 한다.
[3차 채점] WA.
우산이 있는 지점에도 비는 내린다.
"""
import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, h, d = map(int, input().split())
    grid = [input() for _ in range(n)]
    nodes = []
    for col in range(n):
        for row in range(n):
            if grid[col][row] != '.':
                nodes.append((col, row))
    dists = [[0 for row in range(len(nodes))] for col in range(len(nodes))]
    for a in range(len(nodes) - 1):
        for b in range(a + 1, len(nodes)):
            dist = abs(nodes[a][0] - nodes[b][0]) + abs(nodes[a][1] - nodes[b][1])
            dists[a][b] = dists[b][a] = dist
    for idx, (col, row) in enumerate(nodes):
        if grid[col][row] == 'S':
            start = idx
        elif grid[col][row] == 'E':
            end = idx
    heap = [(dist, h - dist + 1, node, (1 << start) | (1 << node)) for node, dist in enumerate(dists[start])\
            if dist <= h and node != start]
    heapq.heapify(heap)
    while heap:
        curr_dist, curr_health, curr_node, bit_visit = heapq.heappop(heap)
        if curr_node == end:
            print(curr_dist)
            return
        for next_node, dist in enumerate(dists[curr_node]):
            if bit_visit & (1 << next_node) == 0:
                if dist <= d:
                    heapq.heappush(heap, (curr_dist + dist, curr_health, next_node, bit_visit | (1 << next_node)))
                elif dist - d < curr_health:
                    heapq.heappush(heap, (curr_dist + dist, curr_health - (dist - d), next_node, bit_visit | (1 << next_node)))
    print(-1)


if __name__ == '__main__':
    main()
