import sys


def floyd_warshall():
    for k in range(n):
        for s in range(n):
            for e in range(n):
                graph[s][e] = min(graph[s][e], graph[s][k] + graph[k][e])


n, m, r = map(int, sys.stdin.readline().split())
num_item = list(map(int, sys.stdin.readline().split()))
graph = [[16 for i in range(n)] for j in range(n)]
for road in range(r):
    a, b, l = map(int, sys.stdin.readline().split())
    graph[a - 1][b - 1], graph[b - 1][a - 1] = l, l

floyd_warshall()

max_item = 0
for start in range(n):
    sum_item = num_item[start]
    for end in range(n):
        if start == end or graph[start][end] > m:
            continue
        sum_item += num_item[end]
    max_item = max(max_item, sum_item)
print(max_item)