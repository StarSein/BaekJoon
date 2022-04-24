import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    s = input()

    for a_num_digit in range(1, 4):
        a = int(s[:a_num_digit])
        for b_num_digit in range(a_num_digit, 4):
            b = int(s[-b_num_digit:])
            if ''.join(map(str, range(a, b + 1))) == s:
                print(a, b)
                return


if __name__ == '__main__':
    main()
