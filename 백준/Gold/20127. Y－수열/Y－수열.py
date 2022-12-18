from sys import stdin
from typing import List


def solution(N: int, arr: List[int]) -> int:
    descend_cnt = 0
    ascend_k = -1
    for i in range(N - 1):
        if arr[i] > arr[i + 1]:
            descend_cnt += 1
            if arr[0] >= arr[N - 1]:
                ascend_k = i + 1
    ascend_cnt = 0
    descend_k = -1
    for i in range(N - 1):
        if arr[i] < arr[i + 1]:
            ascend_cnt += 1
            if arr[0] <= arr[N - 1]:
                descend_k = i + 1
    if descend_cnt == 0 or ascend_cnt == 0:
        return 0
    if descend_cnt == 1 and ascend_k != -1 and ascend_cnt == 1 and descend_k != -1:
        return min(ascend_k, descend_k)
    elif descend_cnt == 1 and ascend_k != -1:
        return ascend_k
    elif ascend_cnt == 1 and descend_k != -1:
        return descend_k
    else:
        return -1


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    arr = list(map(int, input().split()))
    return N, arr


def main():
    print(solution(*read_input()))


if __name__ == '__main__':
    main()
