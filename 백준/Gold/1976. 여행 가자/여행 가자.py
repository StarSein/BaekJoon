from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    M = int(input())
    matrix = [list(map(int, input().split())) for _ in range(N)]
    route = list(map(lambda x: int(x) - 1, input().split()))
    return N, M, matrix, route


def solution(N: int, M: int, matrix: List[List[int]], route: List[int]) -> str:
    for k in range(N):
        for i in range(N):
            for j in range(N):
                matrix[i][j] |= matrix[i][k] & matrix[k][j]
    for i in range(N):
        matrix[i][i] = 1
    
    able = all(matrix[route[i]][route[i + 1]] for i in range(M - 1))
    return "YES" if able else "NO"


if __name__ == '__main__':
    print(solution(*read_input()))
