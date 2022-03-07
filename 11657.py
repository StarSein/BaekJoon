import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def bellman_ford() -> bool:
        START = 1
        dist[START] = 0
        for i in range(1, n):
            for edge in edge_list:
                curr_node, next_node, cost = edge
                if dist[curr_node] != INF and dist[curr_node] + cost < dist[next_node]:
                    dist[next_node] = dist[curr_node] + cost
        for edge in edge_list:
            curr_node, next_node, cost = edge
            if dist[curr_node] != INF and dist[curr_node] + cost < dist[next_node]:
                return True
        return False
    n, m = map(int, input().split())
    edge_list = [tuple(map(int, input().split())) for edge in range(m)]
    INF = int(1e8)
    dist = [INF] * (n + 1)
    is_infinite = bellman_ford()
    if is_infinite:
        print(-1)
    else:
        for city in range(2, n + 1):
            dist[city] = -1 if dist[city] == INF else dist[city]
            print(dist[city])


if __name__ == '__main__':
    main()
