import sys
import heapq

ENTRANCE = 0


def input():
    return sys.stdin.readline().rstrip()


def main():
    def mst_prim(start: int) -> int:
        heap = [(0, start)]
        is_visited = [False] * (n + 1)
        total_cost = 0
        while len(heap):
            weight, current_node = heapq.heappop(heap)

            if is_visited[current_node]:
                continue
            total_cost += weight
            is_visited[current_node] = True

            for next_node, next_weight in connected[current_node]:
                if not is_visited[next_node]:
                    heapq.heappush(heap, (next_weight, next_node))

        return total_cost

    n, m = map(int, input().split())
    connected = [[] for node in range(n + 1)]
    for road in range(m + 1):
        a, b, c = map(int, input().split())
        connected[a].append([b, 1 - c])
        connected[b].append([a, 1 - c])

    min_fatigue = mst_prim(ENTRANCE) ** 2

    for node in range(n + 1):
        for idx in range(len(connected[node])):
            connected[node][idx][1] = 1 - connected[node][idx][1]

    max_fatigue = (n - mst_prim(ENTRANCE)) ** 2

    res = max_fatigue - min_fatigue
    print(res)


if __name__ == '__main__':
    main()
