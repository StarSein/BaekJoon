import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def get_avr():
        total_sum = 0
        cnt_zero = 0
        for col in range(n):
            total_sum += sum(circles[col])
            cnt_zero += circles[col].count(0)
        cnt_nonzero = n * m - cnt_zero
        if cnt_nonzero:
            return total_sum / cnt_nonzero
        else:
            return 0

    n, m, t = map(int, input().split())
    circles = [list(map(int, input().split())) for col in range(n)]
    for rotate in range(t):
        x, d, k = map(int, input().split())
        tmp_x = x
        while tmp_x <= n:
            if d == 0:
                circles[tmp_x-1] = circles[tmp_x-1][m-k:] + circles[tmp_x-1][:m-k]
            else:
                circles[tmp_x-1] = circles[tmp_x-1][k:] + circles[tmp_x-1][:k]
            tmp_x += x

        erase_set = set()
        for col in range(n):
            for row in range(m):
                if circles[col][row] and (circles[col][row] == circles[col][row-1]):
                    erase_set.add((col, row))
                    erase_set.add((col, row-1))
        for row in range(m):
            for col in range(n-1):
                if circles[col][row] and (circles[col][row] == circles[col+1][row]):
                    erase_set.add((col, row))
                    erase_set.add((col+1, row))

        if len(erase_set):
            for col, row in erase_set:
                circles[col][row] = 0
        else:
            avr = get_avr()
            if avr == 0:
                break

            for col in range(n):
                for row in range(m):
                    if circles[col][row] > avr:
                        circles[col][row] -= 1
                    elif 0 < circles[col][row] < avr:
                        circles[col][row] += 1

    total_sum = 0
    for col in range(n):
        total_sum += sum(circles[col])
    print(total_sum)


if __name__ == '__main__':
    main()
