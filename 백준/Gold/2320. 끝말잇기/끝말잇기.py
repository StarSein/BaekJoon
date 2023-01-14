from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N = int(input())
    words = [input() for _ in range(N)]
    return N, words


def solution(N: int, words: List[str]) -> int:
    table = {'A': 0, 'E': 1, 'I': 2, 'O': 3, 'U': 4}
    starts = [table[word[0]] for word in words]
    ends = [table[word[-1]] for word in words]
    lengths = [len(word) for word in words]

    BITMASK_SZ = 1 << N
    NUM_CHAR = 5
    dp = [[-1 for j in range(NUM_CHAR)] for i in range(BITMASK_SZ)]
    for j in range(NUM_CHAR):
        dp[0][j] = 0
    for i in range(BITMASK_SZ):
        for j in range(NUM_CHAR):
            if dp[i][j] == -1:
                continue
            for idx, (s, e, l) in enumerate(zip(starts, ends, lengths)):
                if j == s and ~i & (1 << idx):
                    ni = i | (1 << idx)
                    nj = e
                    dp[ni][nj] = max(dp[ni][nj], dp[i][j] + l)
    answer = max(max(dp[i]) for i in range(BITMASK_SZ))
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
