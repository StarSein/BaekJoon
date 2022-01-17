import sys
from collections import deque


input = sys.stdin.readline


def solution() -> bool:
    cnt_w, cnt_o, cnt_l, cnt_f = 0, 0, 0, 0
    while len(word):
        c = word.popleft()
        locals()['cnt_' + c] += 1
        if c == 'w':
            cnt_w += 1
            if cnt_o | cnt_l | cnt_f != 0:
                return False
        elif c == 'o':
            cnt_o += 1
            if cnt_w <= 0:
                return False
        elif c == 'l':
            cnt_l += 1
            if cnt_w == 0 or cnt_o == 0 or cnt_w != cnt_o:
                return False
        elif c == 'f':
            cnt_f += 1
            if cnt_w == 0 or cnt_o == 0 or cnt_w == 0 or not (cnt_w == cnt_o == cnt_l):
                return False

        if cnt_w == cnt_o == cnt_l == cnt_f:
            cnt_w = cnt_o = cnt_l = cnt_f = 0

    if cnt_w == cnt_o == cnt_l == cnt_f:
        return True
    else:
        return False


if __name__ == '__main__':
    word = deque(list(input().rstrip()))
    sol = solution()
    print(int(sol))
