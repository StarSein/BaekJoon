import sys


N, M = map(int, sys.stdin.readline().split())
inp = list(map(int, sys.stdin.readline().split()))
if inp == [0]:
    cnt_lie = M
else:
    cnt_lie = 0
    num_know, l_know = inp.pop(0), inp
    l_check = [False for _ in range(N + 1)]
    for person in l_know:
        l_check[person] = True

    ll_come = []
    for party in range(M):
        temp = list(map(int, sys.stdin.readline().split()))
        if temp == [0]:
            continue
        num_come, l_come = temp.pop(0), temp
        ll_come.append(l_come)

    det_loop = True
    while det_loop:
        det_loop = False
        for group in ll_come:
            heard_truth = False
            for person in group:
                if l_check[person]:
                    heard_truth = True
                    break
            if heard_truth:
                for person in group:
                    if not l_check[person]:
                        det_loop = True
                        l_check[person] = True

    for party in range(M):
        tell_truth = False
        for person in ll_come[party]:
            if l_check[person]:
                tell_truth = True
                break
        if not tell_truth:
            cnt_lie += 1

print(cnt_lie)
