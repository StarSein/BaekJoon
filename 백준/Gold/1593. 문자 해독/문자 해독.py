from sys import stdin
from collections import Counter


def input():
    return stdin.readline().rstrip()


def read_test_case():
    g, s_size = map(int, input().split())
    W = input()
    S = input()
    return g, s_size, W, S


def solution(g: int, s_size: int, W: str, S: str) -> int:
    w_counter = Counter(W)
    s_counter = Counter(S[:g])
    mismatch_set = set()
    for k, v in w_counter.items():
        if s_counter[k] != v:
            mismatch_set.add(k)
    answer = 0
    if not mismatch_set:
        answer += 1
    for r in range(g, s_size):
        l = r - g
        right = S[r]
        left = S[l]
        s_counter[right] += 1
        if s_counter[right] == w_counter[right]:
            mismatch_set.discard(right)
        else:
            mismatch_set.add(right)
        s_counter[left] -= 1
        if s_counter[left] == w_counter[left]:
            mismatch_set.discard(left)
        else:
            mismatch_set.add(left)

        if not mismatch_set:
            answer += 1
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
