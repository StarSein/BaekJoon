import sys
from typing import List


input = sys.stdin.readline


def main():
    n = int(input())
    print(solution(n))


def solution(n: int) -> str:
    if n == 1:
        return '0'
    elif n == 2:
        return '1'
    else:
        pass

    pn_list = make_primes(n)
    num_case = 0

    lp, rp = 0, 1
    current_sum = pn_list[lp] + pn_list[rp]
    while lp < rp:
        if current_sum == n:
            num_case += 1
            current_sum -= pn_list[lp]
            lp += 1
        elif current_sum > n:
            current_sum -= pn_list[lp]
            lp += 1
        else:
            rp += 1
            current_sum += pn_list[rp]

    if pn_list[-1] == n:
        num_case += 1

    return f'{num_case}'


def make_primes(n: int) -> List[int]:
    is_prime = [True] * (n + 1)
    primes = []
    for num in range(2, n + 1):
        if is_prime[num]:
            primes.append(num)
            next_num = num * 2
            while next_num <= n:
                is_prime[next_num] = False
                next_num += num
    return primes


if __name__ == '__main__':
    main()
