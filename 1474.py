import sys
from collections import deque


input = sys.stdin.readline


def solution() -> str:
    total_underbar = m
    for idx, word in enumerate(word_list):
        total_underbar -= len(word)

    default_num = total_underbar // (n - 1)
    num_addition = total_underbar % (n - 1)

    underbar_list = [default_num] * (n - 1)
    pos_uppers = []
    pos_lowers = deque()
    for pos in range(1, n):
        if is_upper(word_list[pos][0]):
            pos_uppers.append(pos)
        else:
            pos_lowers.append(pos)
    k = 0
    while k < num_addition:
        if len(pos_lowers):
            pos_add = pos_lowers.popleft()
        else:
            pos_add = pos_uppers.pop()
        underbar_list[pos_add-1] += 1
        k += 1

    res = [word_list[0]]
    for idx, num in enumerate(underbar_list, start=1):
        res.append(num * '_')
        res.append(word_list[idx])
    return ''.join(res)


def is_upper(c: chr) -> bool:
    if 65 <= ord(c) < 97:
        return True
    else:
        return False


if __name__ == '__main__':
    n, m = map(int, input().split())
    word_list = [input().rstrip() for i in range(n)]
    sol = solution()
    print(sol)
