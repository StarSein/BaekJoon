import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    r, s = map(int, input().split())
    photo = [list(input()) for _ in range(r)]

    min_height = r
    for row in range(s):
        for col in range(r - 1, -1, -1):
            if photo[col][row] == 'X':
                height = 0
                while photo[col + height + 1][row] == '.':
                    height += 1
                min_height = min(min_height, height)
                break

    for col in range(r - 1, -1, -1):
        for row in range(s):
            if photo[col][row] == 'X':
                photo[col][row] = '.'
                photo[col + min_height][row] = 'X'
    for col in photo:
        print(''.join(col))


if __name__ == '__main__':
    main()
