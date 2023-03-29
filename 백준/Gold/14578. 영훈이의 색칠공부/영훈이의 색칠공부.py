from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return int(input())


def solution(N: int) -> int:
    MOD = int(1e9) + 7
    if N == 1:
        return 0
    if N == 2:
        return 2

    case_red = 1
    for i in range(N, 1, -1):
        case_red = (case_red * i) % MOD

    A = [0 for _ in range(N + 1)]
    B = [0 for _ in range(N + 1)]
    A[2] = 1
    B[2] = 1
    for i in range(2, N):
        A[i + 1] = (i * A[i] + B[i]) % MOD
        B[i + 1] = (i * A[i]) % MOD
    case_blue = B[N]

    return (case_red * case_blue) % MOD


if __name__ == '__main__':
    print(solution(read_input()))
