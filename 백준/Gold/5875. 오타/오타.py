"""

ps:  1  0  1  2  1  0 -1 -2
ss: -2 -3 -2 -3 -4 -3 -2 -1
    -1  2 -1  0  3  2  1  0
    -4 -1 -4 -5
        v        v  v  v

"""
from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return input()


def solution(s: str) -> int:
    n = len(s)
    if n & 1:
        return 0
    arr = [1 if c == '(' else -1 for c in s]
    ps = arr[:]
    for i in range(1, len(ps)):
        ps[i] += ps[i - 1]
    ss = arr[:]
    for i in range(len(ss) - 2, -1, -1):
        ss[i] += ss[i + 1]

    pc = [True for _ in range(n)]
    pc[0] = ps[0] > 0
    for i in range(1, n):
        pc[i] = min(pc[i - 1], ps[i] >= 0)
    sc = [True for _ in range(n)]
    sc[n - 1] = ss[n - 1] < 0
    for i in range(n - 2, -1, -1):
        sc[i] = min(sc[i + 1], ss[i] <= 0)

    ans = 0
    if arr[0] == -1 and ps[0] - 2 * arr[0] == -ss[1]:
        ans += 1
    if arr[n - 1] == 1 and ps[n - 1] - 2 * arr[n - 1] == 0:
        ans += 1
    for i in range(1, n - 1):
        if pc[i - 1] and sc[i + 1] and ps[i] - 2 * arr[i] + ss[i + 1] == 0:
            ans += 1

    return ans


if __name__ == '__main__':
    print(solution(read_input()))
