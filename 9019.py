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
    dp = [[(A, "")]]
    existing = {A}
    result = None
    while True:
        dp.append([])
        for item in dp[-2]:
            if D(item[0]) not in existing:
                if D(item[0]) == B:
                    result = '%sD' % item[1]
                    break
                else:
                    dp[-1].append((D(item[0]), '%sD' % item[1]))
                    existing.add(D(item[0]))
            if S(item[0]) not in existing:
                if S(item[0]) == B:
                    result = '%sS' % item[1]
                    break
                else:
                    dp[-1].append((S(item[0]), '%sS' % item[1]))
                    existing.add((S(item[0])))
            if L(item[0]) not in existing:
                if L(item[0]) == B:
                    result = '%sL' % item[1]
                    break
                else:
                    dp[-1].append((L(item[0]), '%sL' % item[1]))
                    existing.add((L(item[0])))
            if R(item[0]) not in existing:
                if R(item[0]) == B:
                    result = '%sR' % item[1]
                    break
                else:
                    dp[-1].append((R(item[0]), '%sR' % item[1]))
                    existing.add((R(item[0])))
        if result:
            break
    print(result)
