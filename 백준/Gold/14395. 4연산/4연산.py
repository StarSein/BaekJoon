from sys import stdin
from collections import deque, defaultdict
from math import isqrt

def input():
    return stdin.readline().rstrip()


def read_input():
    s, t = map(int, input().split())
    return s, t


def solution(s: int, t: int) -> str:
    if s == t:
        return '0'

    prev_opr = defaultdict(str)
    dq = deque((s ** 2, s * 2, 1))
    if s != 2:
        prev_opr[s ** 2], prev_opr[s * 2], prev_opr[1] = '*', '+', '/'
    else:
        prev_opr[4], prev_opr[1] = '*', '/'
    possible = False
    while dq:
        cur = dq.popleft()

        if cur == t:
            possible = True
            break

        nex = cur ** 2
        if not prev_opr[nex] and nex <= t:
            prev_opr[nex] = '*'
            dq.append(nex)
        nex = cur * 2
        if not prev_opr[nex] and nex <= t:
            prev_opr[nex] = '+'
            dq.append(nex)

    if not possible:
        return '-1'

    answer = []
    num = t
    while num != s:
        e = prev_opr[num]
        answer.append(e)
        if e == '*':
            num = isqrt(num)
        elif e == '+':
            num = num // 2
        else:
            num = s
    return ''.join(answer[::-1])


if __name__ == '__main__':
    print(solution(*read_input()))
