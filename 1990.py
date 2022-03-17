import sys
from math import sqrt


def input():
    return sys.stdin.readline().rstrip()


def main():
    def make_palindromes(length: int, pos: int):
        if pos == length // 2 + 1:
            palindromes.add(int(''.join(num)))
            return

        min_digit = 1 if pos == 0 else 0
        for digit in range(min_digit, 10):
            num[pos] = num[-pos-1] = str(digit)
            make_palindromes(length, pos + 1)

    def is_prime(n: int) -> bool:
        for div in range(2, int(sqrt(n)) + 1):
            if n % div == 0:
                return False
        return True

    a, b = map(int, input().split())
    palindromes = set()
    for cnt_digit in range(1, 8):
        num = ['0'] * cnt_digit
        make_palindromes(cnt_digit, 0)
    answer_list = []
    for num in palindromes:
        if a <= num <= b and is_prime(num):
            answer_list.append(num)
    answer_list.sort()
    for num in answer_list:
        print(num)
    print(-1)


if __name__ == '__main__':
    main()
