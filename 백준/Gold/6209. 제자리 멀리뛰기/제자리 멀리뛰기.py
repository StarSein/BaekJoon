from sys import stdin
from typing import List

def input():
    return stdin.readline().rstrip()


def solution(d: int, n: int, m: int, spots: List[int]) -> int:
    spots.sort()

    def is_able(dist: int) -> bool:
        cnt = 0
        prev_spot = 0
        for i in range(n):
            if spots[i] - prev_spot < dist:
                cnt += 1
            else:
                prev_spot = spots[i]
        if d - prev_spot < dist:
            cnt += 1
        return cnt <= m

    lp = 1
    rp = int(1e9)
    ans = 0
    while lp <= rp:
        mid = (lp + rp) // 2
        if is_able(mid):
            ans = mid
            lp = mid + 1
        else:
            rp = mid - 1
    return ans


if __name__ == '__main__':
    D, N, M = map(int, input().split())
    print(solution(D, N, M, [int(input()) for _ in range(N)]))
