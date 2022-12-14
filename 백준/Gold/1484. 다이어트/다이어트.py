"""
(x+1)**2 - x**2 <= 100_000
2x + 1 <= 100_000
x <= 50_000
제곱수를 5만 개까지만 나열하면 된다.

두 제곱수 a, b에 대해 (a, b)가 b - a = G 를 만족한다고 하면,
x < b이고 x != a인 x 중에 b - x = G를 만족하는 x는 존재하지 않고,
y > a이고 y != b인 y 중에 y - a = G를 만족하는 y는 존재하지 않는다.
즉 a보다 크면서 조건을 만족하는 b는 유일하고, b보다 작으면서 조건을 만족하는 a는 유일하다.
그러니까 투 포인터를 써도 되겠다.

try 1) WA
원인: "가능한 몸무게가 없을 때는 -1을 출력한다"를 누락함.
"""
from sys import stdin
from math import isqrt
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    return int(input())


def solution(G: int) -> List[int]:
    SZ = 50_000
    nums = [i ** 2 for i in range(1, SZ + 1)]
    lp, rp = 0, 1
    answers = []
    while lp < rp:
        diff = nums[rp] - nums[lp]
        if diff >= G:
            if diff == G:
                answers.append(isqrt(nums[rp]))
            lp += 1
        elif rp + 1 < SZ:
            rp += 1
        else:
            break
    if answers:
        return answers
    else:
        return [-1]


if __name__ == '__main__':
    print(*solution(read_input()), sep='\n')
