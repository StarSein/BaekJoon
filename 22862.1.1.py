import sys
import queue

input = sys.stdin.readline


def solution(n, k, seq):
    numOfOddNum = 0
    for i in range(n):
        if seq[i] % 2 != 0:
            numOfOddNum += 1
    if numOfOddNum <= k:
        numOfEvenNum = n - numOfOddNum
        return numOfEvenNum
    subseq = queue.Queue()
    idx = initSubseq(subseq)
    bestLength = subseq.qsize() - k
    while idx < n-1:
        idx = updateSubseq(idx, subseq)
        currentLength = subseq.qsize() - k
        bestLength = max(bestLength, currentLength)
    return bestLength


def initSubseq(subseq):
    idx = 0
    cntOfOddNum = 0 if seq[idx] % 2 == 0 else 1
    while cntOfOddNum < k+1:
        subseq.put(seq[idx])
        idx += 1
        if seq[idx] % 2 != 0:
            cntOfOddNum += 1
    return idx


def updateSubseq(idx, subseq):
    removedNum = subseq.get()
    while removedNum % 2 == 0:
        removedNum = subseq.get()
    subseq.put(seq[idx])
    idx += 1
    while seq[idx] % 2 == 0:
        subseq.put(seq[idx])
        if idx == n-1:
            break

        idx += 1
    return idx


if __name__ == '__main__':
    n, k = map(int, input().split())
    seq = list(map(int, input().split()))
    sol = solution(n, k, seq)
    print(sol)
