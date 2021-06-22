import sys


def floyd_warshall():
    for k in range(1, V + 1):
        for r in range(1, V + 1):
            for s in range(1, V + 1):
                graph[r][s] = min(graph[r][s], graph[r][k] + graph[k][s])


INF = sys.maxsize
V, E = map(int, sys.stdin.readline().split())
K = int(sys.stdin.readline())
graph = [[INF for i in range(V + 1)] for j in range(V + 1)]
for _ in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u][v] = w
floyd_warshall()
for row in range(1, V + 1):
    if K == row:
        print(0)
    elif graph[K][row] == INF:
        print("INF")
    else:
        print(graph[K][row])