import sys


def fibonacci():
    l_fibonacci = [0, 1]
    while l_fibonacci[-1] <= 1000000000:
        l_fibonacci.append(l_fibonacci[-2] + l_fibonacci[-1])
    return l_fibonacci


T = int(sys.stdin.readline())
l_fibonacci = fibonacci()
for case in range(T):
    n = int(sys.stdin.readline())
    seq = []
    for i in range(-1, -len(l_fibonacci) - 1, -1):
        if l_fibonacci[i] <= n:
            seq.append(l_fibonacci[i])
            n -= l_fibonacci[i]
            if not n:
                break
    seq.reverse()
    print(*seq)