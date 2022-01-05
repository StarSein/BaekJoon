import sys

input = sys.stdin.readline


def euclid(a, b):
    if b == 0:
        return a
    else:
        return euclid(b, a % b)


def solution(d1, d2):
    matrix = [[0 for i in range(j)] for j in range(d2 + 1)]
    for denominator in range(d1, d2+1):
        for numerator in range(denominator):
            gcd = euclid(denominator, numerator)
            denom = denominator // gcd
            numer = numerator // gcd
            matrix[denom][numer] = 1
    cntSeat = 0
    for col in range(1, d2+1):
        for row in range(col):
            if matrix[col][row] == 1:
                cntSeat += 1
    return cntSeat


if __name__ == '__main__':
    d1, d2 = map(int, input().split())
    sol = solution(d1, d2)
    print(sol)
