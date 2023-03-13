from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return input()


def solution(inp: str) -> str:
    PPAP = ['P', 'P', 'A', 'P']
    stack = []
    for c in inp:
        stack.append(c)
        if len(stack) >= 4 and all(stack[i] == PPAP[i] for i in range(-4, 0)):
            del stack[-3:]

    return "PPAP" if stack == ["P"] else "NP"


if __name__ == '__main__':
    print(solution(read_input()))
