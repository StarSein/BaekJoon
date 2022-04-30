"""
만들어지는 서로 다른 수식의 개수는 괄호 쌍의 개수가 k일 때, 2^k개다.
이때 입력으로 받은 수식만 제외하고 사전순으로 출력하면 된다.
괄호 쌍은 최대 10개이므로, 완전탐색한 결과를 출력하면 된다.
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def make_expression(pos: int, bit_removed: int):
        if pos == len(bracket_pair_list):
            expression_set.add(''.join([char for idx, char in enumerate(inp_expression) if not (1 << idx) & bit_removed]))
            return
        left_idx, right_idx = bracket_pair_list[pos]
        make_expression(pos + 1, bit_removed | (1 << left_idx) | (1 << right_idx))
        make_expression(pos + 1, bit_removed)

    inp_expression = input()
    bracket_pair_list = []
    stack = []
    for idx, char in enumerate(inp_expression):
        if char == '(':
            stack.append(idx)
        elif char == ')':
            bracket_pair_list.append((stack.pop(), idx))
    expression_set = set()
    make_expression(0, 0)
    expression_set.remove(inp_expression)
    print('\n'.join(sorted(list(expression_set))))


if __name__ == '__main__':
    main()
