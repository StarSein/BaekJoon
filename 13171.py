import sys


def fast_modulo_exp(b, exp, m):
    result = 1
    while exp > 1:
        if exp & 1:
            result = result * b % m
        b = b ** 2 % m
        exp >>= 1
    return b * result % m


A = int(sys.stdin.readline())
X = int(sys.stdin.readline())
print(fast_modulo_exp(A, X, 1000000007))