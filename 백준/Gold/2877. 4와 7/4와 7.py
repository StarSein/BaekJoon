from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return int(input())


def solution(K: int) -> str:
    binary_str = bin(K + 1)
    table = binary_str.maketrans({'0': '4', '1': '7'})
    answer = binary_str.translate(table)[3:]
    return answer


if __name__ == '__main__':
    print(solution(read_input()))
