import sys


input = sys.stdin.readline
ASCII_a = 97
ASCII_z = 122
MAX_LEN = 10_000
MIN_LEN = 0
NO_STRING = "-1"


def solution() -> str:
    cnt_table = {chr(i): [] for i in range(ASCII_a, ASCII_z+1)}
    for idx, char in enumerate(w):
        cnt_table[char].append(idx)

    max_len = MIN_LEN
    min_len = MAX_LEN

    for char in cnt_table.keys():
        pos_list = cnt_table[char]
        if len(pos_list) >= k:
            for idx in range(len(pos_list) - k + 1):
                curr_len = pos_list[idx+k-1] - pos_list[idx] + 1
                max_len = max(max_len, curr_len)
                min_len = min(min_len, curr_len)

    if max_len == MIN_LEN:
        return NO_STRING

    return "%d %d" % (min_len, max_len)


if __name__ == '__main__':
    t = int(input())
    for tc in range(t):
        w = input().rstrip()
        k = int(input())
        sol = solution()
        print(sol)
