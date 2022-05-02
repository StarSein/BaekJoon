"""
한 골목에서 내야 하는 최대 요금을 이분 탐색으로 찾자.
각 최대 요금 수준 이내에서 목표 지점에서 도달할 수 있는지 여부를 알기 위해
다익스트라 알고리즘을 사용하자.
이때 최대 요금을 벗어난 간선으로는 이동하지 않는다.
그러면 시간 복잡도는 O(NlogN * 30) <= log2(10^9)는 약 30
"""
import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def main():
    def dijkstra(cost_limit: int) -> int:
        heap = [(0, a)]
        visited = [False] * (n + 1)
        while heap:
            total_cost, curr_node = heapq.heappop(heap)
            if curr_node == b:
                return total_cost
            if visited[curr_node]:
                continue
            visited[curr_node] = True
            for next_node, next_cost in graph[curr_node]:
                if not visited[next_node] and next_cost <= cost_limit:
                    # 방문 여부 체크: 로직의 관점에서 필요는 없지만 불필요한 힙 삽입 연산에 따른 시간 소모를 줄이기 위함
                    heapq.heappush(heap, (total_cost + next_cost, next_node))
        return c + 1

    n, m, a, b, c = map(int, input().split())
    graph = [[] for _ in range(n + 1)]
    max_cost = 0
    for edge in range(m):
        u, v, cost = map(int, input().split())
        max_cost = max(max_cost, cost)
        graph[u].append((v, cost))
        graph[v].append((u, cost))

    left, right = 1, max_cost + 1
    min_cost = max_cost + 1
    while left <= right:
        mid = left + (right - left) // 2
        if dijkstra(mid) <= c:
            min_cost = min(min_cost, mid)
            right = mid - 1
        else:
            left = mid + 1
    print(min_cost if min_cost != max_cost + 1 else -1)


if __name__ == '__main__':
    main()
