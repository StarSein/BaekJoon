import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    target = tuple(i for i in range(1, N + 1))

    dq = deque()
    visited = set()

    initStat = tuple(arr)
    dq.append(initStat)
    visited.add(initStat)

    cntFlip = 0
    while dq:
        dqSize = len(dq)
        while dqSize:
            curStat = dq.popleft()
            
            if curStat == target:
                return cntFlip

            curList = list(curStat)
            for i in range(N - K + 1):
                nextStat = tuple(curList[:i] + curList[i:i+K][::-1] + curList[i+K:])
                if nextStat not in visited:
                    dq.append(nextStat)
                    visited.add(nextStat)
            dqSize -= 1
        cntFlip += 1
    return -1


if __name__ == '__main__':
    N, K = map(int, input().split())
    arr = list(map(int, input().split()))

    print(solution())
