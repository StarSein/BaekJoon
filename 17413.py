import sys
from collections import deque


input = sys.stdin.readline
res = []
stack = []
queue = deque()


def solution() -> str:
    is_tag = False
    for idx, char in enumerate(inp_str):
        if char == ' ':
            line_up()
            res.append(' ')
        else:
            if char == '<':
                line_up()
                is_tag = True
            if is_tag:
                queue.append(char)
            else:
                stack.append(char)
            if char == '>':
                line_up()
                is_tag = False
    while len(stack):
        res.append(stack.pop())
    while len(queue):
        res.append(queue.pop())
    return ''.join(res)


def line_up():
    while len(stack):
        res.append(stack.pop())
    while len(queue):
        res.append(queue.popleft())


if __name__ == '__main__':
    inp_str = input().rstrip()
    sol = solution()
    print(sol)