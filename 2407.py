import sys
from functools import reduce


def euclid(a, b):
    if b == 0:
        return a
    else:
        return euclid(b, a % b)


def comb(a, b):
    b = min(b, a-b)
    l_numer = []
    l_denom = []
    for i in range(b):
        numer = a-i
        denom = b-i
        for p in range(len(l_denom)):
            gcd = euclid(l_denom[p], numer)
            numer //= gcd
            l_denom[p] //= gcd
        for q in range(len(l_numer)):
            gcd = euclid(l_numer[q], denom)
            denom //= gcd
            l_numer[q] //= gcd
        if numer != 1:
            l_numer.append(numer)
        if denom != 1:
            l_denom.append(denom)
    numerator = reduce(lambda x, y: x * y, l_numer)
    denominator = reduce(lambda x, y: x * y, l_denom)
    return numerator//denominator


n, m = map(int, sys.stdin.readline().split())
print(comb(n, m))