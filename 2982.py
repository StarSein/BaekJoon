"""
다익스트라 알고리즘을 사용하자.
이때 힙에서 반환되는 curr_time의 해당 도로에 국왕이 있는 경우,
국왕이 해당 도로를 다 통과한 시각을 힙에 넣고 넘기자.
아니다.
국왕이 이미 있는 도로에 새로 진입하는 것이 불가능하니까
힙에 새로운 요소를 넣어줄 때 국왕의 통행으로 인한 지연 시간을 반영하자.
"""
import sys
import heapq
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    a, b, k, g = map(int, input().split())
    king_nodes = list(map(int, input().split()))
    graph = [[] for node in range(n + 1)]
    king_costs = dict()
    for road in range(m):
        u, v, l = map(int, input().split())
        graph[u].append((v, l))
        graph[v].append((u, l))
        if u in king_nodes and v in king_nodes:
            king_costs[(u, v)] = king_costs[(v, u)] = l

    time = 0
    king_schedule = deque()
    for i in range(g - 1):
        curr_node, next_node = king_nodes[i], king_nodes[i+1]
        king_schedule.append((time, curr_node, next_node))
        time += king_costs[(curr_node, next_node)]
    king_schedule.append((time, 0, 0))

    blocked_set = set()
    while king_schedule and king_schedule[0][0] <= k:
        blocked_set.clear()
        blocked_set.add(king_schedule[0][1])
        blocked_set.add(king_schedule[0][2])
        king_schedule.popleft()

    def dijkstra(start: int, end: int) -> int:
        heap = [(k, start)]
        visited = [False] * (n + 1)
        while heap:
            curr_time, curr_node = heapq.heappop(heap)

            if visited[curr_node]:
                continue
            if curr_node == end:
                return curr_time

            while king_schedule and king_schedule[0][0] <= curr_time:
                blocked_set.clear()
                blocked_set.add(king_schedule[0][1])
                blocked_set.add(king_schedule[0][2])
                king_schedule.popleft()

            visited[curr_node] = True
            if curr_node in blocked_set:
                for next_node, weight in graph[curr_node]:
                    if not visited[next_node]:
                        if next_node in blocked_set:
                            heapq.heappush(heap, (king_schedule[0][0] + weight, next_node))
                        else:
                            heapq.heappush(heap, (curr_time + weight, next_node))
            else:
                for next_node, weight in graph[curr_node]:
                    if not visited[next_node]:
                        heapq.heappush(heap, (curr_time + weight, next_node))

    print(dijkstra(a, b) - k)


if __name__ == '__main__':
    main()
