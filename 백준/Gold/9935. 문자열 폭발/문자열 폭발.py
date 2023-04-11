from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return input(), list(input())


def solution(S: str, P: str) -> str:
    p_len = len(P)
    stack = []
    for c in S:
        stack.append(c)
        if len(stack) >= p_len:
            flag = True
            for i in range(-1, -p_len - 1, -1):
                if stack[i] != P[i]:
                    flag = False
                    break
            if flag:
                del stack[-p_len:]
    return ''.join(stack) if stack else "FRULA"


if __name__ == '__main__':
    print(solution(*read_input()))
