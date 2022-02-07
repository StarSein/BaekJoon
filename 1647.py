import sys
import heapq


input = sys.stdin.readline
START = 1


def solution():
    is_visited = [False] * (n + 1)
    min_heap = [(0, START)]
    mst_list = []
    while len(min_heap):
        cost, arrive = heapq.heappop(min_heap)
        if is_visited[arrive]:
            continue

        mst_list.append(cost)
        is_visited[arrive] = True
        for idx, edge in enumerate(connected[arrive]):
            heapq.heappush(min_heap, (edge[0], edge[1]))

    res = sum(mst_list) - max(mst_list)
    print(res)


if __name__ == '__main__':
    n, m = map(int, input().split())
    connected = [[] for node in range(n + 1)]
    for edge in range(m):
        a, b, c = map(int, input().split())
        connected[a].append((c, b))
        connected[b].append((c, a))
    solution()
