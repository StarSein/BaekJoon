import sys


A, B, C = map(int, sys.stdin.readline().split())
mod = A % C
set_mod = {mod}
count = 1
while count != B:
    mod *= mod
    mod %= C
    if mod in set_mod:
        break
    set_mod.add(mod)
if count != 1:
    B = B % (B - count)
sys.stdout.write(str((mod**B % C)))
