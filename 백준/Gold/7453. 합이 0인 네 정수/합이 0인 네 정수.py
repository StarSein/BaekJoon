from sys import stdin
from collections import Counter
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    n = int(input())
    arr0, arr1, arr2, arr3 = [], [], [], []
    for _ in range(n):
        i0, i1, i2, i3 = map(int, input().split())
        arr0.append(i0)
        arr1.append(i1)
        arr2.append(i2)
        arr3.append(i3)
    return n, arr0, arr1, arr2, arr3


def solution(n: int, arr0: List[int], arr1: List[int], arr2: List[int], arr3: List[int]) -> int:
    ab_dict = dict()
    for i0 in arr0:
        for i1 in arr1:
            two_sum = i0 + i1
            if two_sum in ab_dict:
                ab_dict[two_sum] += 1
            else:
                ab_dict[two_sum] = 1

    answer = 0
    for i2 in arr2:
        for i3 in arr3:
            target = -(i2 + i3)
            if target in ab_dict:
                answer += ab_dict[target]
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
