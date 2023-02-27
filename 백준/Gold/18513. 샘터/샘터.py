from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, K = map(int, input().split())
    oasis = list(map(int, input().split()))
    return N, K, oasis


def solution(N: int, K: int, oasis: List[int]) -> int:
    visit_set = set(oasis)
    dq = deque(oasis)
    answer = 0
    dist = 0
    home_cnt = 0
    while dq:
        dist += 1
        size = len(dq)
        for _ in range(size):
            x = dq.popleft()
            for nx in (x - 1, x + 1):
                if nx not in visit_set:
                    visit_set.add(nx)
                    dq.append(nx)
                    answer += dist
                    home_cnt += 1
                    if home_cnt == K:
                        return answer
    return -1


if __name__ == '__main__':
    print(solution(*read_input()))
