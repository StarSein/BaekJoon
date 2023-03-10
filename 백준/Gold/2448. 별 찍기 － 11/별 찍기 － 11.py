from sys import stdin
from typing import Iterable


def input():
    return stdin.readline().rstrip()


def read_input():
    return int(input())


def solution(N: int) -> Iterable[str]:
    answer = [[' ' * (N - 1 - i)] for i in range(N)]

    def star(start: int, end: int):
        if start + 3 == end:
            answer[start].append('*')
            answer[start + 1].append('* *')
            answer[start + 2].append('*****')
            return
        mid = (start + end) // 2
        star(start, mid)
        star(mid, end)

        empty_cnt = 2 * (end - mid) - 1
        for i in range(mid, end):
            answer[i].append(' ' * empty_cnt)
            empty_cnt -= 2

        star(mid, end)

    star(0, N)

    for i in range(N):
        answer[i].append(' ' * (N - 1 - i))

    return map(lambda x: ''.join(x), answer)


if __name__ == '__main__':
    print(*solution(read_input()), sep='\n')