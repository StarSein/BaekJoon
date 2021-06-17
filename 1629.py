import sys


A, B, C = map(int, sys.stdin.readline().split())
mod = A % C
result = mod ** B
if B != 1:
    i = 2
    l_mod = [mod]
    while i <= B:
        mod = mod ** 2 % C
        l_mod.append(mod)
        i *= 2
    result = 1
    str_binB = str(bin(B))
    for j in filter(lambda x: str_binB[x] == '1', range(2, len(str_binB))):
        result = result * l_mod[len(str_binB) - 1 - j] % C
print(result)
