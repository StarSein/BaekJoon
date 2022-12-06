from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return input()


def solution(S: str) -> int:
    stack = [[1, 0]]
    for i, c in enumerate(S):
        if c == '(':
            stack.append([int(S[i - 1]), 0])
        elif c == ')':
            weight, length = stack.pop()
            stack[-1][1] += weight * length
        elif i + 1 == len(S) or S[i + 1] != '(':
            stack[-1][1] += 1
    ans = stack[0][1]
    return ans


if __name__ == '__main__':
    print(solution(read_input()))
