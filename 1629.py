import sys


A, B, C = map(int, sys.stdin.readline().split())
mod = A % C
result = mod ** B
if B != 1:
    i = 2
    d_mod = {1: mod}
    while i <= B:
        mod = mod ** 2 % C
        d_mod[i] = mod
        i *= 2
    result = 1
    for j in range(len(d_mod) - 1, 0, -1):
        if 2 ** j <= B:
            result = result * d_mod[2**j] % C
            B -= 2 ** j
print(result)

