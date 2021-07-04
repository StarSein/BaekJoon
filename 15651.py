import sys


def search(k):
    if k == M:
        print(*stack)
    else:
        for i in range(1, N + 1):
            stack.append(i)
            search(k + 1)
            stack.pop()

N, M = map(int, sys.stdin.readline().split())
stack = []
search(0)
