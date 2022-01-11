import sys
from typing import List


input = sys.stdin.readline
INIT_SCORE, INIT_CHAIN = 0, 0
SCORE, CHAIN = 0, 1 # index 에 부여한 이름처럼 사용


def solution(num_step: int, score_list: List[int]) -> int:
    dp = [[] for _ in range(num_step + 1)]  # dp: 0번째 계단(시작점)부터 num_step 번째 계단까지, 그 계단에 서 있는 케이스의 정보
    dp[0].append((INIT_SCORE, INIT_CHAIN))  # 각 케이스의 정보: tuple(지금까지 쌓은 점수, 지금 밟은 연속한 계단의 개수)
    for i in range(num_step):
        for idx, val in enumerate(dp[i]):
            if val[CHAIN] < 2:
                dp[i+1].append((val[SCORE] + score_list[i], val[CHAIN] + 1))    # 현재 위치에서의 케이스를 계단 한 칸 위로 올려보냄

            if i + 1 < num_step:
                dp[i+2].append((val[SCORE] + score_list[i+1], INIT_CHAIN + 1))  # 현재 위치에서의 케이스를 계단 두 칸 위로 올려보냄

        dp[i+1].sort(key=lambda x: (x[CHAIN], -x[SCORE]))   # 다음 계단에 서 있는 케이스들을 정렬함
        fittest_list = []                                   # 쓸모 있는 케이스만 담는 리스트: 각 CHAIN 값에 대해 SCORE 값이 가장 높은 케이스만
        chain = dp[i+1][0][CHAIN]                           # chain: 가장 낮은 CHAIN 값
        for idx, val in enumerate(dp[i+1]):
            if val[CHAIN] == chain:
                fittest_list.append(val)
                chain += 1
        dp[i+1] = fittest_list                              # 쓸모 있는 케이스들만 다음 계단에 서 있게 함

    best_value = max(dp[num_step])[SCORE]                   # num_step 번째 계단에 서 있는 케이스 중 가장 높은 점수를 반환함
    return best_value


if __name__ == '__main__':
    num_step = int(input())
    score_list = [int(input()) for step in range(num_step)]
    sol = solution(num_step, score_list)
    print(sol)