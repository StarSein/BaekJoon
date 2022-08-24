import sys


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    N, Q = map(int, input().split())
    a = [0] + list(map(int, input().split()))
    for i in range(N):
        a[i+1] ^= a[i]

    ret = 0
    for i in range(Q):
        s, e = map(int, input().split())
        ret ^= a[e] ^ a[s-1]
    return ret


if __name__ == '__main__':
    print(solution())
