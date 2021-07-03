import sys


N, M = map(int, sys.stdin.readline().split())
inp = list(map(int, sys.stdin.readline().split()))
if inp == [0]:
    cnt_lie = M
else:
    cnt_lie = 0
    num_know, l_know = inp.pop(0), inp
    l_check = [False] * (N + 1)
    for person in l_know:
        l_check[person] = True
    ll_come = []
    for party in range(M):
        temp = list(map(int, sys.stdin.readline().split()))
        if temp == [0]:
            continue
        num_come, l_come = temp.pop(0), temp
        heard_truth = False
        for person in l_come:
            if l_check[person]:
                heard_truth = True
                break
        if heard_truth:
            for person in l_come:
                l_check[person] = True
        ll_come.append(l_come)
    for party in range(M):
        tell_truth = False
        for person in ll_come[party]:
            if l_check[person]:
                tell_truth = True
                break
        if not tell_truth:
            cnt_lie += 1
print(cnt_lie)