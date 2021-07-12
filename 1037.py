import sys


N = int(sys.stdin.readline())
l_div = list(map(int, sys.stdin.readline().split()))
res = min(l_div) * max(l_div)
print(res)
