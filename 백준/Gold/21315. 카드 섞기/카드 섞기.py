"""
N <= 1000이므로, K<=9이다
두 순서쌍 (2, K)의 경우의 수는 9^2 = 81
각 경우의 수마다 섞기 작업에 따른 연산의 최대 개수 9000 (리스트 슬라이싱 이용)
시간 복잡도 O(K^2 * N)
완전 탐색으로 풀 수 있겠다.
"""
import sys
from math import log2, floor
from copy import deepcopy
from typing import List


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    result = list(map(int, input().split()))
    init = list(range(1, n + 1))

    def shuffle(cards: List[int], k: int) -> List[int]:
        left = cards[-(2**k):]
        right = cards[:-(2**k)]
        for i in range(1, k + 2):
            right = left[:-(2**(k-i+1))] + right
            left = left[-(2**(k-i+1)):]
        return left + right

    max_k = floor(log2(n))
    for first_k in range(1, max_k + 1):
        for second_k in range(1, max_k + 1):
            if shuffle(shuffle(deepcopy(init), first_k), second_k) == result:
                print(first_k, second_k)
                return


if __name__ == '__main__':
    main()
