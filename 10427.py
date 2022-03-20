import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    t = int(input())
    for tc in range(t):
        inp = list(map(int, input().split()))
        a_list = inp[1:]

        a_list.sort()
        prefix_sum = [0]
        for a in a_list:
            prefix_sum.append(prefix_sum[-1] + a)

        answer = 0
        SIZE = len(a_list)
        for m in range(1, SIZE):
            min_add_debt = int(1e8)
            for lp in range(SIZE - m):
                curr_add_debt = a_list[lp+m] * (m + 1) - (prefix_sum[lp+m+1] - prefix_sum[lp])
                min_add_debt = min(min_add_debt, curr_add_debt)
            answer += min_add_debt

        print(answer)


if __name__ == '__main__':
    main()
