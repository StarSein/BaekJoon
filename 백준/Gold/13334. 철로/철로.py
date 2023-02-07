"""
가로 방향으로 스위핑을 하면서
입장을 처리하는 큐 - 오른쪽 점이 들어오는 경우
퇴장을 처리하는 큐 - 왼쪽 점이 들어오는 경우
퇴장 큐는 철로의 왼쪽, 입장 큐는 철로의 오른쪽 위치를 기준으로 한다
"""
from sys import stdin
from collections import deque
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_test_case():
    n = int(input())
    pairs = [tuple(map(int, input().split())) for _ in range(n)]
    d = int(input())
    return n, pairs, d


def solution(n: int, pairs: List[Tuple[int, int]], d: int) -> int:
    pos_list = []
    s_list = []
    e_list = []
    for s, e in pairs:
        if s > e:
            s, e = e, s
        if e - s > d:
            continue
        pos_list.append(s)
        pos_list.append(e)
        s_list.append(s)
        e_list.append(e)
    pos_list.sort()
    s_list.sort()
    e_list.sort()
    dq_s = deque(s_list)
    dq_e = deque(e_list)

    answer = 0
    cnt = 0
    for pos in pos_list:
        while dq_e and dq_e[0] <= pos + d:
            dq_e.popleft()
            cnt += 1
        while dq_s and dq_s[0] < pos:
            dq_s.popleft()
            cnt -= 1
        answer = max(answer, cnt)
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
