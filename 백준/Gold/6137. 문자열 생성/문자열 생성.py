"""
S를 투 포인터로 탐색하면서, 우선순위가 높은 것을 T의 마지막에 추가하자.
    우선순위
    1. 사전순으로 더 빠른 문자
    2. 같은 문자일 경우, 각각의 문자 다음의 문자를 서로 비교했을 때 더 빠른 문자 (재귀적으로 비교)
        S가 하나의 문자로 이루어진 문자열일 경우(최악의 케이스) O(N^2)
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    s = [input() for _ in range(n)]

    def compare(lp: int, rp: int) -> bool:
        if s[lp] != s[rp] or lp >= rp:
            return s[lp] < s[rp]
        else:
            return compare(lp+1, rp-1)

    LEFT, RIGHT = True, False
    t = []
    lp, rp = 0, n - 1
    while lp <= rp:
        if compare(lp, rp) == LEFT:
            t.append(s[lp])
            lp += 1
        else:
            t.append(s[rp])
            rp -= 1

    WIDTH_LIMIT = 80
    i = 0
    while i < n:
        print(''.join(t[i:i+WIDTH_LIMIT]))
        i += WIDTH_LIMIT


if __name__ == '__main__':
    main()
