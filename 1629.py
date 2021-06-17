import sys


def fast_mod_exp(b, exp, m):
    result = 1
    while exp > 1:
        if exp & 1:
            result = (result * b) % m
        b = b ** 2 % m
        exp >>= 1
    return (b * result) % m


A, B, C = map(int, sys.stdin.readline().split())
print(fast_mod_exp(A, B, C))
