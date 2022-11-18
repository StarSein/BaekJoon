"""
두 단어 각각을 오름차순으로 정렬했을 때 일치하면,
두 단어는 서로 순서만 다른 것으로,
다른 위치에 있는 문자의 개수를 세고 비용에 추가하면 된다.

DP로 구현 시 O(N * L^2 * logL) (L은 문장 및 단어의 길이 <= 50)
"""

from sys import stdin
from typing import List

input = lambda: stdin.readline().rstrip()


def match(a: str, b: str) -> bool:
    return ''.join(sorted(a)) == ''.join(sorted(b))


def calc_cost(a: str, b: str) -> int:
    cnt_mismatch = 0
    for ai, bi in zip(a, b):
        if ai != bi:
            cnt_mismatch += 1
    return cnt_mismatch


def solution(s: str, n: int, words: List[str]) -> int:
    s_size = len(s)
    INF = 10000
    dp = [INF] * (s_size + 1)
    dp[0] = 0
    for i in range(len(dp) - 1):
        for word in words:
            sz = len(word)
            if i + sz > s_size:
                continue
            subs = s[i:i + sz]
            if match(word, subs):
                cost = calc_cost(word, subs)
                dp[i + sz] = min(dp[i + sz], dp[i] + cost)

    ans = dp[s_size] if dp[s_size] != INF else -1
    return ans


if __name__ == '__main__':
    sentence = input()
    N = int(input())
    word_list = [input() for _ in range(N)]
    print(solution(sentence, N, word_list))
