import sys
from collections import deque

input = sys.stdin.readline


def solution(seq):
    totalBackStep = 0
    queue = deque(seq)
    sortingList = []
    while queue:
        stud = queue.popleft()
        totalBackStep += insertStud(sortingList, stud)

    return totalBackStep


def insertStud(sortingList, stud):
    if len(sortingList) == 0 or sortingList[-1] < stud:
        numOfBackStep = 0
        sortingList.append(stud)
    else:
        for idx in range(len(sortingList)):
            if stud < sortingList[idx]:
                numOfBackStep = len(sortingList) - idx
                sortingList.insert(idx, stud)
                break

    return numOfBackStep


if __name__ == '__main__':
    p = int(input())
    for tc in range(1, p+1):
        inp = list(map(int, input().split()))
        num = inp[0]
        seq = inp[1:]
        sol = solution(seq)
        print(tc, sol)