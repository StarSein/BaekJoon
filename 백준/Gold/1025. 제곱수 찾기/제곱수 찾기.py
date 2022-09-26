"""
for 시작 행 in 9
    for 시작 열 in 9
        for 행 공차 in 9
            for 열 공차 in 9

O(N^4) = 9^4 = 3^8 = 6561

완전 탐색을 해서 나오는 경우의 수는 6561 보다 훨씬 적다.
나올 수 있는 수의 최댓값이 10^10 이므로, 1 ~ 10^5 까지 제곱한 수들을 미리 구해놓는 것보다는
그때그때 만들어진 수의 제곱근 값이 정수인지를 판별하면 되겠다.

[try 1] WA
반례)
    입력:
    1 1
    4
    정답: 4
    출력: -1
원인) 수열의 길이가 1일 경우 행 공차와 열 공차 모두 0이어도 된다는 점을 간과함
해결) 따로 조건 분기

[try 2] WA
반례)
    입력:
    3 3
    611
    141
    111
    정답: 64
    출력: 16
원인) 수열의 길이를 '인덱스 범위를 벗어나지 않는 최대한'으로 고정했던 점.
해결) 수열의 길이도 1~9까지 다양하게 설정. 따라서 시간 복잡도는 O(N^5)

"""
from sys import stdin
from math import sqrt

input = lambda: stdin.readline().rstrip()


def solution() -> int:
    N, M = map(int, input().split())
    MAX_LEN = max(N, M)
    table = [list(map(lambda x: int(x), iter(input()))) for _ in range(N)]
    answer = -1

    for seq_len in range(1, MAX_LEN + 1):
        for r_start in range(N):
            for c_start in range(M):
                for r_step in range(-N + 1, N, 1):
                    for c_step in range(-M + 1, M, 1):
                        if seq_len > 1 and (r_step | c_step) == 0:
                            continue
                        sequence = []
                        for i in range(seq_len):
                            row = r_start + r_step * i
                            col = c_start + c_step * i
                            if 0 <= row < N and 0 <= col < M:
                                sequence.append(str(table[row][col]))
                            else:
                                break
                        result = int(''.join(sequence))
                        if sqrt(result).is_integer():
                            answer = max(answer, result)
    return answer


if __name__ == '__main__':
    print(solution())
