"""
    0001 0010
    0001 0011

    0001 0022
         0001


    0001 0010 0011 0100

누적 0001 0011 0000 0100
"""
from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    nums = list(map(int, input().split()))
    return N, nums


def solution(N: int, nums: List[int]) -> int:
    SZ = 30
    pref_xor = [0 for _ in range(N)]
    pref_xor[0] = nums[0]
    for i in range(1, N):
        pref_xor[i] = pref_xor[i - 1] ^ nums[i]

    bit_cnt = [[0 for j in range(SZ)] for i in range(N)]
    for i in range(N):
        for j in range(SZ):
            if pref_xor[i] & (1 << j):
                bit_cnt[i][j] = 1

    pref_cnt = [[0 for j in range(SZ)] for i in range(N)]
    pref_cnt[0] = bit_cnt[0][:]
    for i in range(1, N):
        for j in range(SZ):
            pref_cnt[i][j] = pref_cnt[i - 1][j] + bit_cnt[i][j]

    answer = sum(1 << j for j in range(SZ) if bit_cnt[0][j])
    for i in range(1, N):
        for j in range(SZ):
            if bit_cnt[i][j]:
                answer += (i + 1 - pref_cnt[i - 1][j]) * (1 << j)
            else:
                answer += pref_cnt[i - 1][j] * (1 << j)
    return answer


def main():
    print(solution(*read_input()))


if __name__ == '__main__':
    main()
