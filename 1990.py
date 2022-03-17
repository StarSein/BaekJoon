import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def find_prime_numbers(min_num: int, max_num: int):
        is_prime = [True] * (max_num + 1)
        for num in range(2, max_num // 2 + 1):
            if is_prime[num]:
                next_num = num * 2
                while next_num <= max_num:
                    is_prime[next_num] = False
                    next_num += num
        for num in range(min_num, max_num + 1):
            if is_prime[num]:
                prime_numbers.append(str(num))

    def is_palindrome(s: str) -> bool:
        i = 0
        while i < len(s) // 2:
            if s[i] != s[-i-1]:
                return False
            i += 1
        return True

    a, b = map(int, input().split())
    prime_numbers = []
    find_prime_numbers(a, b)
    for prime in prime_numbers:
        if is_palindrome(prime):
            print(prime)
    print("-1")


if __name__ == '__main__':
    main()
