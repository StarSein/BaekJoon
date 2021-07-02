import sys


def lower_bound(start, end, n):
    if vector[end] < n:
        vector.append(n)
    elif vector[start] > n:
        vector[start] = n
    else:
        if start == end:
            return 0
        elif start == end - 1:
            if vector[start] != n:
                vector[end] = n
        else:
            mid = (start + end) // 2
            if vector[mid] < n:
                start = mid
            elif vector[mid] > n:
                end = mid
            else:
                return 0
            return lower_bound(start, end, n)


N = int(sys.stdin.readline())
l_cable = [tuple(map(int, sys.stdin.readline().split())) for _ in range(N)]
l_cable.sort()
vector = [l_cable[0][1]]
for idx in range(1, N):
    lower_bound(0, len(vector) - 1, l_cable[idx][1])
print(N - len(vector))