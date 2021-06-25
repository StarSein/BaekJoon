import sys


def floyd_warshall():
    for k in range(1, N + 1):
        for r in range(1, N + 1):
            for s in range(1, N + 1):
                graph[r][s] = min(graph[r][s], graph[r][k] + graph[k][s])


INF = sys.maxsize
N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
graph = [[INF for i in range(N + 1)] for j in range(N + 1)]
for info in range(M):
    departure, arrival, fare = map(int, sys.stdin.readline().split())
    graph[departure][arrival] = fare
floyd_warshall()
dep, arr = map(int, sys.stdin.readline().split())
print(graph[dep][arr])
