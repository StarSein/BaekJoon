from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, d, k, c = map(int, input().split())
    sushi = [int(input()) for _ in range(N)]
    return N, d, k, c, sushi


def solution(N: int, d: int, k: int, c: int, sushi: List[int]) -> int:
    sushi.extend(sushi[:k])

    counter = [0 for _ in range(d + 1)]

    cur_kind = 0
    for i in range(k):
        counter[sushi[i]] += 1
        if counter[sushi[i]] == 1:
            cur_kind += 1
    answer = cur_kind + int(counter[c] == 0)

    for i in range(k, len(sushi)):
        right = sushi[i]
        counter[right] += 1
        if counter[right] == 1:
            cur_kind += 1

        left = sushi[i - k]
        counter[left] -= 1
        if counter[left] == 0:
            cur_kind -= 1
        answer = max(answer, cur_kind + int(counter[c] == 0))

    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
