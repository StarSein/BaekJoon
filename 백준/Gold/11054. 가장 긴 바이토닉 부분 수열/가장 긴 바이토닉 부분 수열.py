from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    A = list(map(int, input().split()))
    return N, A


def solution(N: int, A: List[int]) -> int:
    ldp = [1 for _ in range(N)]
    for i in range(1, N):
        ldp[i] = 1 + max((ldp[j] for j in range(i) if A[j] < A[i]), default=0)
    rdp = [1 for _ in range(N)]
    for i in range(N - 2, -1, -1):
        rdp[i] = 1 + max((rdp[j] for j in range(i + 1, N) if A[j] < A[i]), default=0)
    answer = max(ldp[i] + rdp[i] - 1 for i in range(N))
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
