import sys
from collections import deque


input = sys.stdin.readline


def solution():
    q_possess = deque(y_list)
    is_possess = [False] * n
    while len(q_possess):
        potion = q_possess.popleft()
        is_possess[potion-1] = True
        for idx, follower in enumerate(followers[potion]):
            if is_possess[follower-1]:
                continue

            for i in range(len(preceders[follower])):
                if potion in preceders[follower][i]:
                    preceders[follower][i].discard(potion)
                    if len(preceders[follower][i]) == 0:
                        q_possess.append(follower)

    print(sum(is_possess))
    for potion, is_able in enumerate(is_possess, start=1):
        if is_able:
            print(potion, end=' ')


if __name__ == '__main__':
    n, m = map(int, input().split())
    preceders = [[set()] for node in range(n + 1)]
    followers = [set() for node in range(n + 1)]
    is_recipe = [False] * (n + 1)
    for inp in range(m):
        recipe = list(map(int, input().split()))
        k = recipe[0]
        r = recipe[-1]
        if is_recipe[r]:
            preceders[r].append(set())
        else:
            is_recipe[r] = True
        for i in range(1, k + 1):
            followers[recipe[i]].add(r)
            preceders[r][-1].add(recipe[i])
    l = int(input())
    y_list = list(map(int, input().split()))
    solution()
