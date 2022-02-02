import sys
from collections import deque


input = sys.stdin.readline


def solution():
    for idx, potion in enumerate(y_list):
        cnt_precede[potion] = 0
    q_possess = deque(y_list)
    list_potion = []
    while len(q_possess):
        potion = q_possess.popleft()
        list_potion.append(potion)
        for idx, follower in enumerate(followers[potion]):
            cnt_precede[follower] -= 1
            if cnt_precede[follower] == 0:
                q_possess.append(follower)

    print(len(list_potion))
    list_potion.sort()
    for idx, potion in enumerate(list_potion):
        print(potion, end=' ')


if __name__ == '__main__':
    n, m = map(int, input().split())
    cnt_precede = [0] * (n + 1)
    followers = [[] for node in range(n + 1)]
    for inp in range(m):
        recipe = list(map(int, input().split()))
        k = recipe[0]
        r = recipe[-1]
        cnt_precede[r] = k
        for i in range(1, k + 1):
            followers[recipe[i]].append(r)
    l = int(input())
    y_list = list(map(int, input().split()))
    solution()
