from sys import stdin
from collections import Counter
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    T = int(input())
    n = int(input())
    A = list(map(int, input().split()))
    m = int(input())
    B = list(map(int, input().split()))
    return T, n, A, m, B


def solution(T: int, n: int, A: List[int], m: int, B: List[int]) -> int:
    psumA = [0]
    for num in A:
        psumA.append(psumA[-1] + num)

    cntA = Counter((psumA[i] - psumA[j] for i in range(1, n + 1) for j in range(i)))

    psumB = [0]
    for num in B:
        psumB.append(psumB[-1] + num)

    cntB = Counter((psumB[i] - psumB[j] for i in range(1, m + 1) for j in range(i)))

    answer = sum(cntA[a] * cntB[T - a] for a in cntA.keys())
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))

