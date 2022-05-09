"""
위상 정렬
"""
import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    followers = dict()
    cnt_precede = dict()
    childs = dict()
    for name in list(input().split()):
        followers[name] = []
        cnt_precede[name] = 0
        childs[name] = []
    m = int(input())
    for edge in range(m):
        x, y = input().split()
        followers[y].append(x)
        cnt_precede[x] += 1

    roots = [name for name, cnt in cnt_precede.items() if cnt == 0]
    dq = deque(roots)
    while dq:
        cur_name = dq.popleft()
        for descend in followers[cur_name]:
            cnt_precede[descend] -= 1
            if cnt_precede[descend] == 0:
                childs[cur_name].append(descend)
                dq.append(descend)

    print(len(roots))
    print(*sorted(roots))
    for name, child_list in sorted(childs.items()):
        print(*[name, len(child_list), *sorted(child_list)])


if __name__ == '__main__':
    main()