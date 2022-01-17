import sys
from itertools import permutations

input = sys.stdin.readline


def solution() -> int:
    MAX_NUM = int(''.join([str(digit) for digit in range(9, 9-k, -1)]))
    is_prime_list = [True] * (MAX_NUM + 1)
    primes = []
    for num in range(2, MAX_NUM + 1):
        if is_prime_list[num]:
            primes.append(num)
            for i in range(num * 2, MAX_NUM + 1, num):
                is_prime_list[i] = False

    sums_of_two_primes = set()
    for i in range(len(primes)):
        primeA = primes[i]
        for j in range(i + 1, len(primes)):
            primeB = primes[j]
            sum_of_two = primeA + primeB
            if sum_of_two > MAX_NUM:
                break
            sums_of_two_primes.add(sum_of_two)

    multiples_of_two_primes = set()
    for i in range(len(primes)):
        primeA = primes[i]
        for j in range(i, len(primes)):
            primeB = primes[j]
            multiple_of_two = primeA * primeB
            if multiple_of_two > MAX_NUM:
                break
            multiples_of_two_primes.add(multiple_of_two)

    cnt = 0
    num_list = [str(i) for i in range(10)]
    for case in permutations(num_list, k):
        if case[0] == '0':
            continue

        num = int(''.join(case))
        if num in sums_of_two_primes:
            while num % m == 0:
                num //= m
            if num in multiples_of_two_primes:
                cnt += 1

    return cnt


if __name__ == '__main__':
    k, m = map(int, input().split())
    sol = solution()
    print(sol)
