import sys


def input():
    return sys.stdin.readline().rstrip()


def solution() -> str:
    n = int(input())
    S = list(input())
    T = list(input())
    i, j = map(int, input().split())

    twoCoin = ['_', '_']
    twoCoin[1] = S.pop(j)
    twoCoin[0] = S.pop(i)

    able = [True, True]
    for k in range(2):
        idx = 0
        si = 0
        ti = 0
        while ti < n and si < len(S):
            if S[si] != T[ti]:
                if idx < 2 and twoCoin[idx] == T[ti]:
                    idx += 1
                    si -= 1
                else:
                    able[k] = False
                    break
            ti += 1
            si += 1

        if able[k] and twoCoin[idx:] != T[ti:]:
            able[k] = False

        if k == 0:
            S.reverse()
            T.reverse()
            twoCoin.reverse()

    if able[0] or able[1]:
        return "YES"
    else:
        return "NO"


if __name__ == '__main__':
    print(solution())
