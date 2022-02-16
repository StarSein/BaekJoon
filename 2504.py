import sys


input = sys.stdin.readline

pair = {'(': ')',   # 여는 괄호를 key, 닫는 괄호를 value로 하는 딕셔너리
        '[': ']'}


def solution() -> str:
    stack = []      # (여는 괄호, 임시 변수 값)을 저장하는 스택
    tmp_val = 0     # 괄호 안의 계산값을 저장하는 임시 변수
    for idx, char in enumerate(inp_str):
        if char == '(' or char == '[':
            stack.append((char, tmp_val))           # 닫는 괄호와의 대조를 위해 괄호 모양을, 추후 덧셈을 위해 임시 변수의 값(왼쪽 괄호값)을 따로 스택에 저장해둔다
            tmp_val = 0                             # '왼쪽 괄호값'을 곱셈에 포함시키지 않기 위해 임시 변수를 0으로 초기화한다
        else:
            if len(stack) == 0:                     # 그른 괄호1) - 여는 괄호가 없는데 닫는 괄호가 나타난 경우
                return '0'
            stored_char, stored_val = stack.pop()
            if pair[stored_char] != char:           # [그른 괄호2) - 여는 괄호와 닫는 괄호가 서로 대응되지 않는 경우
                return '0'
            else:
                if tmp_val == 0:                    # 여는 괄호와 닫는 괄호 사이에 다른 올바른 괄호가 없는 경우
                    if stored_char == '(':
                        tmp_val = 2
                    else:
                        tmp_val = 3
                else:                               # 여는 괄호와 닫는 괄호 사이에 한 쌍 이상의 올바른 괄호가 존재하는 경우
                    if stored_char == '(':
                        tmp_val *= 2
                    else:
                        tmp_val *= 3
                tmp_val += stored_val               # tmp_val(오른쪽 괄호값)과 stored_val(왼쪽 괄호값)을 더한다
    if len(stack):  # (그른 괄호3 - 문자열 순회를 마쳤는데 닫히지 않은 괄호가 존재하는 경우
        return '0'
        
    res = str(tmp_val)
    return res


if __name__ == '__main__':
    inp_str = input().rstrip()
    sol = solution()
    print(sol)
