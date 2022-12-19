from sys import stdin
from collections import Counter
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    arr = list(map(int, input().split()))
    return N, arr


def solution(N: int, arr: List[int]) -> int:
    arr.sort()
    counter = Counter(arr)
    zero_exist = any(x == 0 for x in arr)
    key_list = list(counter.keys())
    if zero_exist:
        key_list.remove(0)
    sz = len(key_list)
    two_sum_set = set(key_list[i] + key_list[j] for i in range(sz - 1) for j in range(i + 1, sz))
    answer = 0
    for num in arr:
        if num in two_sum_set:
            answer += 1
        elif zero_exist:
            if num == 0:
                if counter[num] >= 3:
                    answer += 1
            else:
                if counter[num] >= 2:
                    answer += 1
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
