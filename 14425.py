import sys


input = sys.stdin.readline
EOL = "EOL"


if __name__ == '__main__':
    n, m = map(int, input().split())

    root = dict()
    for insert_str in range(n):
        inp_str = input().rstrip()
        current = root
        for i in range(len(inp_str)):
            if inp_str[i] not in current:
                current[inp_str[i]] = dict()
            current = current[inp_str[i]]
        current[EOL] = True

    cnt = 0
    for check_str in range(m):
        inp_str = input().rstrip()
        current = root
        is_not_exist = False
        for i in range(len(inp_str)):
            if inp_str[i] not in current:
                is_not_exist = True
                break

            current = current[inp_str[i]]
        if is_not_exist:
            continue

        if EOL in current:
            cnt += 1

    print(cnt)
