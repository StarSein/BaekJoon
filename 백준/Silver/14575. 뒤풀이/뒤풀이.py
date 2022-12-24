from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, T = map(int, input().split())
    inquiries = [tuple(map(int, input().split())) for _ in range(N)]
    return N, T, inquiries


def solution(N: int, T: int, inquiries: List[Tuple[int, int]]) -> int:
    def is_able(S: int) -> bool:
        upper_bound = 0
        for Li, Ri in inquiries:
            if S < Li:
                return False
            upper_bound += min(Ri, S)
        return upper_bound >= T

    lower_bound = sum(Li for Li, Ri in inquiries)
    if lower_bound > T:
        return -1
    lp, rp = 1, int(1e6)
    answer = -1
    while lp <= rp:
        mid = (lp + rp) >> 1
        if is_able(mid):
            answer = mid
            rp = mid - 1
        else:
            lp = mid + 1
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
