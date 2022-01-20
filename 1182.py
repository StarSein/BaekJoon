import sys
from typing import List

input = sys.stdin.readline

subseq_set = set()


def solution() -> int:
    sum_subseq = 0
    seq.sort()
    backtrack(0, sum_subseq, [])
    return len(subseq_set)


def backtrack(pos: int, sum_subseq: int, subseq: List[int]):
    if sum_subseq == s:
        str_sub = [str(x) for x in subseq]
        subseq_set.add(''.join(str_sub))
        return
    elif sum_subseq > s:
        return

    if pos < n:
        subseq.append(seq[pos])
        backtrack(pos + 1, sum_subseq + seq[pos], subseq)
        subseq.pop()
        backtrack(pos + 1, sum_subseq, subseq)


if __name__ == '__main__':
    n, s = map(int, input().split())
    seq = list(map(int, input().split()))
    sol = solution()
    print(sol)
