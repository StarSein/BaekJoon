import sys


input = sys.stdin.readline
ASCII_A = 65

subseq = []
subseq_set = set()


def solution() -> int:
    backtrack(0, 0)
    return len(subseq_set)


def backtrack(pos: int, sum_subseq: int):
    if sum_subseq == s and len(subseq) > 0:
        subseq_set.add(''.join(subseq))

    if pos < n:
        subseq.append(chr(ASCII_A + pos))
        backtrack(pos + 1, sum_subseq + seq[pos])
        subseq.pop()
        backtrack(pos + 1, sum_subseq)


if __name__ == '__main__':
    n, s = map(int, input().split())
    seq = list(map(int, input().split()))
    sol = solution()
    print(sol)
