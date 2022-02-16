import sys


input = sys.stdin.readline

pair = {'(': ')',
        '[': ']'}


def solution() -> str:
    stack = []
    tmp_val = 0
    for idx, char in enumerate(inp_str):
        if char == '(' or char == '[':
            stack.append((char, tmp_val))
            tmp_val = 0
        else:
            if len(stack) == 0:
                return '0'
            stored_char, stored_val = stack.pop()
            if pair[stored_char] != char:
                return '0'
            else:
                if tmp_val == 0:
                    if stored_char == '(':
                        tmp_val = 2
                    else:
                        tmp_val = 3
                else:
                    if stored_char == '(':
                        tmp_val *= 2
                    else:
                        tmp_val *= 3
                tmp_val += stored_val

    if len(stack):
        return '0'
    else:
        res = str(tmp_val)
    return res


if __name__ == '__main__':
    inp_str = input().rstrip()
    sol = solution()
    print(sol)
