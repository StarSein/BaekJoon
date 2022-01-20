import sys
from typing import List

input = sys.stdin.readline

subseq_set = set()


def solution() -> int:
    backtrack(0, 0, [])
    return len(subseq_set)


def backtrack(pos: int, sum_subseq: int, subseq: List[int]):
    if sum_subseq == s and len(subseq) > 0:
        str_sub = [str(v) for i, v in enumerate(subseq)]
        subseq_set.add(''.join(str_sub))

    if pos < n:
        subseq.append(pos)
        backtrack(pos + 1, sum_subseq + seq[pos], subseq)
        subseq.pop()
        backtrack(pos + 1, sum_subseq, subseq)


if __name__ == '__main__':
    n, s = map(int, input().split())
    seq = list(map(int, input().split()))
    sol = solution()
    print(sol)
