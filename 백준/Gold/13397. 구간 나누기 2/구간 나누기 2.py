from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    arr = list(map(int, input().split()))
    return N, M, arr


def solution(N: int, M: int, arr: List[int]) -> int:
    def able(limit: int) -> bool:
        seg_cnt = 1
        min_val, max_val = 10001, 0
        for val in arr:
            min_val = min(min_val, val)
            max_val = max(max_val, val)
            if max_val - min_val > limit:
                seg_cnt += 1
                min_val = max_val = val
        return seg_cnt <= M

    lp, rp = 0, max(arr) - min(arr)
    ans = 0
    while lp <= rp:
        mid = (lp + rp) >> 1
        if able(mid):
            ans = mid
            rp = mid - 1
        else:
            lp = mid + 1
    return ans


if __name__ == '__main__':
    print(solution(*read_input()))
