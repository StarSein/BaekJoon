from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return input(), input()


def solution(S: str, T: str) -> int:
    S = list(S)
    T = list(T)
    while len(S) < len(T):
        back = T.pop()
        if back == 'B':
            T.reverse()
    return 1 if S == T else 0


if __name__ == '__main__':
    print(solution(*read_input()))
