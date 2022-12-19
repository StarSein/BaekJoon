from sys import stdin
from itertools import pairwise
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M, L = map(int, input().split())
    S = [int(input()) for _ in range(M)]
    Q = [int(input()) for _ in range(N)]
    return N, M, L, S, Q


def solution(N: int, M: int, L: int, S: List[int], Q: List[int]) -> List[int]:
    S.insert(0, 0)
    segments = [b - a for a, b in pairwise(S)]
    segments.append(L - S[M])

    def is_able(lower_bound: int, _q: int) -> bool:
        cut_cnt = 0
        cur_length = 0
        for seg in segments:
            if cur_length < lower_bound:
                cur_length += seg
            else:
                cut_cnt += 1
                cur_length = seg
        if cur_length < lower_bound:
            cut_cnt -= 1
        return cut_cnt >= _q
    answers = []
    for q in Q:
        lp, rp = 1, L
        answer = -1
        while lp <= rp:
            mid = (lp + rp) >> 1
            if is_able(mid, q):
                answer = mid
                lp = mid + 1
            else:
                rp = mid - 1
        answers.append(answer)
    return answers


if __name__ == '__main__':
    print(*solution(*read_input()), sep='\n')
