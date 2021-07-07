import sys


def comb(n, k):
    if k == 0:
        return 1
    else:
        upside = n
        downside = k
        n -= 1
        k -= 1
        while k > 0:
            u_gcd = euclid(upside, k)
            d_gcd = euclid(downside, n)
            upside *= n // d_gcd
            upside //= u_gcd
            downside *= k // u_gcd
            downside //= d_gcd
            n -= 1
            k -= 1
        return upside // downside


def euclid(a, b):
    if b == 0:
        return a
    else:
        return euclid(b, a % b)


N, K = map(int, sys.stdin.readline().split())
print(comb(N, K) % 10007)
