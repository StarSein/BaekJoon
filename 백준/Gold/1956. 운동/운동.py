from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    V, E = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(E)]
    return V, E, edges


def solution(V: int, E: int, edges: List[Tuple[int, int, int]]) -> int:
    INF = int(1e9)
    matrix = [[INF for j in range(V + 1)] for i in range(V + 1)]
    for a, b, c in edges:
        matrix[a][b] = c

    for k in range(1, V + 1):
        for i in range(1, V + 1):
            for j in range(1, V + 1):
                matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j])

    answer = INF
    for start in range(1, V + 1):
        for end in range(1, V + 1):
            answer = min(answer, matrix[start][end] + matrix[end][start])

    return -1 if answer == INF else answer


if __name__ == '__main__':
    print(solution(*read_input()))
