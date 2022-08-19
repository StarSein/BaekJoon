import sys
from collections import defaultdict


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    NUM_ALPHA = 10
    weight_dict = defaultdict(int)

    for word in words:
        for pos, char in enumerate(word):
            weight_dict[ord(char) - ASCII_A] += 10 ** (len(word) - pos - 1)

    weight_list = sorted(weight_dict.values(), reverse=True)

    return sum([i * weight for i, weight in zip(range(NUM_ALPHA - 1, -1, -1), weight_list)])


if __name__ == '__main__':
    N = int(input())
    words = [input() for _ in range(N)]

    ASCII_A = ord('A')

    print(solution())
