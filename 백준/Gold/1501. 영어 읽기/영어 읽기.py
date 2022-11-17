"""
각 단어마다 해당 단어와 동일한 것으로 간주되는 단어들(garbled words)을 저장해놓지 않으면,
10000 * 100 * 10000 * 100 만큼의 연산을 해야 한다.
(문장의 개수 * 각 문장별 단어의 개수 * 사전에 있는 단어의 개수 * 단어의 길이)

garbled words를 일일히 저장해놓는 것과 개수를 세는 것은 지수 시간 복잡도를 요구하므로,
다른 방법이 필요하다.

문자열을 일일히 저장하는 게 아니라, 특정한 그룹에 저장해놓으면 개수를 O(1)에 셀 수 있다.
(맨앞의 문자, 맨뒤의 문자, A의 개수, B의 개수, ..., Z의 개수, a의 개수, b의 개수, ..., z의 개수)
튜플의 형태로 key를 만들어 딕셔너리에 저장하면 된다.
"""
from sys import stdin
from collections import defaultdict
from typing import Tuple, List


input = lambda: stdin.readline().rstrip()


def str_to_key(w: str) -> Tuple[str, str, Tuple[int, ...]]:
    NUM_CHAR = 52
    UPPER_A = ord('A')
    LOWER_a = ord('a')
    cnt = [0] * NUM_CHAR
    for c in w:
        if c.isupper():
            cnt[ord(c) - UPPER_A] += 1
        else:
            cnt[ord(c) - LOWER_a + 26] += 1

    return tuple((w[0], w[-1], tuple((cnt[i] for i in range(NUM_CHAR)))))


def solution(n: int, words: Tuple[str], m: int, sentences: Tuple[str]) -> List[int]:
    table = defaultdict(int)
    for word in words:
        key = str_to_key(word)
        table[key] += 1

    nums = [1] * m
    for i, sentence in enumerate(sentences):
        for word in sentence.split():
            key = str_to_key(word)
            nums[i] *= table[key]

    return nums


if __name__ == '__main__':
    N = int(input())
    word_tuple = tuple(input() for _ in range(N))
    M = int(input())
    sentence_tuple = tuple(input() for _ in range(M))
    print(*solution(N, word_tuple, M, sentence_tuple), sep='\n')
