import sys
import heapq


input = sys.stdin.readline


def solution():
    min_heap = [(0, start, start)]
    heapq.heapify(min_heap)
    is_visited_from = [False] * (n + 1)
    while len(min_heap):
        cost, depart, current = heapq.heappop(min_heap)
        if is_visited_from[current]:
            continue

        is_visited_from[current] = depart
        if current == end:
            print(cost)
            break
        for idx, bus in enumerate(connected[current]):
            add_cost, arrive = bus[0], bus[1]
            heapq.heappush(min_heap, (cost + add_cost, current, arrive))

    depart = end
    st_route = [depart]
    while depart != start:
        depart = is_visited_from[depart]
        st_route.append(depart)
    print(len(st_route))
    while len(st_route):
        print(st_route.pop(), end=' ')


if __name__ == '__main__':
    n = int(input())
    m = int(input())
    connected = [[] for node in range(n + 1)]
    for bus in range(m):
        depart, arrive, cost = map(int, input().split())
        connected[depart].append((cost, arrive))
    start, end = map(int, input().split())
    solution()
