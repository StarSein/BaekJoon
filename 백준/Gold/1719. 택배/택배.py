from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    INF = int(1e9)

    n, m = map(int, input().split())
    matrix = [[INF for c in range(n + 1)] for r in range(n + 1)]
    for _ in range(m):
        nodeA, nodeB, cost = map(int, input().split())
        matrix[nodeA][nodeB] = min(matrix[nodeA][nodeB], cost)
        matrix[nodeB][nodeA] = min(matrix[nodeB][nodeA], cost)
        
    return n, m, matrix


def solution(n: int, m: int, matrix: List[List[int]]) -> str:
    INF = int(1e9)

    waypoint = [['-' if matrix[r][c] == INF else str(c)
                 for c in range(n + 1)] for r in range(n + 1)]

    for node in range(1, n + 1):
        matrix[node][node] = 0
        waypoint[node][node] = '-'

    for k in range(1, n + 1):
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if matrix[i][j] > matrix[i][k] + matrix[k][j]:
                    matrix[i][j] = matrix[i][k] + matrix[k][j]
                    waypoint[i][j] = waypoint[i][k]

    return '\n'.join(' '.join(waypoint[row][1:]) for row in range(1, n + 1))


if __name__ == '__main__':
    print(solution(*read_input()))
