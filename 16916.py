import sys


input = sys.stdin.readline


def solution() -> int:
    cnt = 0
    for idx, char in enumerate(s):
        if char == p[cnt]:
            cnt += 1
            if cnt == len(p):
                return 1
        else:
            cnt = 0
    return 0


if __name__ == '__main__':
    s = input().rstrip()
    p = input().rstrip()
    sol = solution()
    print(sol)
