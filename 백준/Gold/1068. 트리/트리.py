import sys


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    N = int(input())
    childs = [[] for i in range(N)]
    parents = list(map(int, input().split()))
    deletedNode = int(input())

    ROOT = -1
    for i, v in enumerate(parents):
        if v != -1:
            childs[v].append(i)
        else:
            ROOT = i

    if deletedNode == ROOT:
        return 0

    stack = [ROOT]
    ret = 0
    while stack:
        curNode = stack.pop()

        isLeaf = True
        for child in childs[curNode]:
            if child != deletedNode:
                isLeaf = False
                stack.append(child)
        if isLeaf:
            ret += 1
    return ret


if __name__ == '__main__':
    print(solution())
