import sys


def input():
    return sys.stdin.readline().rstrip()


def getMinWeight(visit: int, nextNode: int) -> int:
    if dpW[nextNode][visit] != -1:
        return dpW[nextNode][visit]

    dpW[nextNode][visit] = min([graph[curNode][nextNode] for curNode in range(N)
                                if visit & 1 << curNode])
    return dpW[nextNode][visit]


def getMinCost(visit: int, numLeft: int) -> int:
    if numLeft <= 0:
        return 0

    if visit == 0:
        return -1

    if dpC[visit] != INF:
        return dpC[visit]

    ret = INF
    for nextNode in range(N):
        if ~visit & (1 << nextNode):
            val = getMinWeight(visit, nextNode) + getMinCost(visit | (1 << nextNode), numLeft - 1)
            if val < ret:
                ret = val

    dpC[visit] = min([getMinWeight(visit, nextNode) + getMinCost(visit | 1 << nextNode, numLeft - 1)
                      for nextNode in range(N) if not visit & 1 << nextNode])
    return dpC[visit]


def solution() -> int:
    return getMinCost(initBit, P - cntY)


if __name__ == '__main__':
    N = int(input())
    graph = [list(map(int, input().split())) for _ in range(N)]

    INF = int(1e8)

    dpC = [INF for c in range(1 << N)]
    dpW = [[-1 for c in range(1 << N)] for r in range(N)]

    initBit = 0
    s = input()
    cntY = 0
    for i, v in enumerate(s):
        if v == 'Y':
            initBit |= 1 << i
            cntY += 1

    P = int(input())

    print(solution())
