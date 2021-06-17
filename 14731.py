import sys


def fast_modulo_exp(b, exp, m):
    result = 1
    while exp > 1:
        if exp & 1:
            result = result * b % m
        b = b ** 2 % m
        exp >>= 1
    return b * result % m


N = int(sys.stdin.readline())
output = 0
for _ in range(N):
    C, K = map(int, sys.stdin.readline().split())
    if K == 1:
        output = (output + C) % 1000000007
    elif K:
        output = (output + K * C * fast_modulo_exp(2, K-1, 1000000007)) % 1000000007
    else:
        pass
print(output)
