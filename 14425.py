import sys


input = sys.stdin.readline


if __name__ == '__main__':
    n, m = map(int, input().split())
    str_set = set()
    for insert_str in range(n):
        inp_str = input().rstrip()
        str_set.add(inp_str)

    cnt = 0
    for check_str in range(m):
        inp_str = input().rstrip()
        if inp_str in str_set:
            cnt += 1

    print(cnt)
