import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def floyd_warshall():
        for via in range(1, n + 1):
            for col in range(1, n + 1):
                for row in range(1, n + 1):
                    graph[col][row] = min(graph[col][row], graph[col][via] + graph[via][row])

    INF = int(1e6)
    n, m = map(int, input().split())
    graph = [[INF for row in range(n + 1)] for col in range(n + 1)]
    for node in range(1, n + 1):
        graph[node][node] = 0

    for road in range(m):
        a, b, time = map(int, input().split())
        graph[a][b] = time

    floyd_warshall()
    k = int(input())
    start_nodes = list(map(int, input().split()))
    min_max_time = INF
    best_nodes = []
    for meet in range(1, n + 1):
        max_time = 0
        for start in start_nodes:
            if graph[start][meet] >= INF or graph[meet][start] >= INF:
                max_time = INF
                break
            max_time = max(max_time, graph[start][meet] + graph[meet][start])
        if max_time < min_max_time:
            min_max_time = max_time
            best_nodes.clear()
            best_nodes.append(meet)
        elif max_time == min_max_time:
            best_nodes.append(meet)

    print(*best_nodes)


if __name__ == '__main__':
    main()
