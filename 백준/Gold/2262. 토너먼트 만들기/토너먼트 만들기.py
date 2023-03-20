from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    n = int(input())
    ranks = list(map(int, input().split()))
    return n, ranks


def solution(n: int, ranks: List[int]) -> int:
    answer = 0
    for i in range(n):
        cur_rank = ranks[i]
        if cur_rank == 1:
            continue
            
        diff = 10000
        for j in range(i - 1, -1, -1):
            if ranks[j] < cur_rank:
                diff = min(diff, cur_rank - ranks[j])
                break
        for j in range(i + 1, n, 1):
            if ranks[j] < cur_rank:
                diff = min(diff, cur_rank - ranks[j])
                break
        answer += diff
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
