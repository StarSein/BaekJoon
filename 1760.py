import sys
sys.setrecursionlimit(100000)


def search(y):
    if y == N:
        return stack
    else:
        for x in range(N):
            if col[x] | diag1[x+y] | diag2[x-y+N-1]:
                continue
            stack.append(x + 1)
            col[x] = diag1[x+y] = diag2[x-y+N-1] = True
            search(y + 1)
            if len(stack) == N:
                break
            col[x] = diag1[x+y] = diag2[x-y+N-1] = False
            stack.pop()


N = int(sys.stdin.readline())
col = [False] * N
diag1 = [False] * (2 * N - 1)
diag2 = [False] * (2 * N - 1)
stack = []
search(0)
for item in stack:
    print(item)