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

[개선] 수열의 길이가 긴 것부터 만들어보고,
결과가 나왔으면 그것보다 작은 길이의 제곱수는 만들 필요가 없다.

[try3] WA
원인) sequence의 길이가 원래 의도한 길이와 같을 때에만 유의미한 값으로 취급했는데,
    [0, 1, 2, 3] 처럼 앞에 0이 오는 수열의 길이도 4로 취급한 점이 오류.
    
[try4] WA
원인) 문자열 '0'를 sequence에 넣어놓고 정수 0 과 비교함.
"""

from sys import stdin
from math import sqrt

input = lambda: stdin.readline().rstrip()


def solution() -> int:
    N, M = map(int, input().split())
    MAX_LEN = max(N, M)
    table = [list(map(lambda x: int(x), iter(input()))) for _ in range(N)]
    answer = -1

    for seq_len in range(MAX_LEN, 0, -1):
        if answer != -1:
            break

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

                        if (seq_len > 1 and sequence[0] == '0') or len(sequence) != seq_len:
                            continue

                        result = int(''.join(sequence))
                        if sqrt(result).is_integer():
                            answer = max(answer, result)
    return answer


if __name__ == '__main__':
    print(solution())
