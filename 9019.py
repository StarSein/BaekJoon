import sys


def D(n):
    return n * 2 % 10000


def S(n):
    if n:
        return n-1
    else:
        return 9999


def L(n):
    if n >= 1000:
        return int(str(n)[1:4] + str(n)[0])
    elif n >= 100:
        return int(str(n)[1:3] + str(n)[0])
    elif n >= 10:
        return int(str(n)[1] + str(n)[0])
    else:
        return n


def R(n):
    if n >= 1000:
        return int(str(n)[3] + str(n)[0:3])
    elif n >= 100:
        return int(str(n)[2] + str(n)[0:2])
    elif n >= 10:
        return int(str(n)[1] + str(n)[0])
    else:
        return n


T = int(sys.stdin.readline())
for _ in range(T):
    A, B = map(int, sys.stdin.readline().split())
    dp = [{(A, "")}]
    result = None
    while True:
        dp.append(set())
        for item in dp[-2]:
            new_item = set()
            new_item.add((D(item[0]), item[1] + 'D'))
            new_item.add((S(item[0]), item[1] + 'S'))
            new_item.add((L(item[0]), item[1] + 'L'))
            new_item.add((R(item[0]), item[1] + 'R'))
            for x in new_item:
                if x[0] == B:
                    result = x[1]
                    break
                dp[-1].add(x)
            if result:
                break
        if result:
            break
    print(result)
