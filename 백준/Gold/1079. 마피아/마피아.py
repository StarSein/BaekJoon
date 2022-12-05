"""
N = 16 일 때, 은진이 선택의 경우의 수
15 * 13 * 11 * 9 * 7 * 5 * 3 * 1 = 약 200만 개
메모이제이션을 위해 필요한 상태 공간의 크기 2^16 = 32000

try 1) WA
원인: 밤에 마피아가 죽인 사람을, 낮에 투표로 죽이는 오류가 발생할 수 있다.
해결: day_killed 를 구하는 식의 조건 분기 수정

try 2) WA
원인: 1. 투표로 죽을 사람을 정하는 두 번째 기준을 간과했다. " 그런 사람이 여러 명일 경우 그중 번호가 가장 작은 사람이 죽는다."
     2. 참가자가 짝수 명 남았을 때 밤이고, 홀수 명 남았을 때 낮이라는 조건도 간과했다.
해결: key 인자의 람다식 수정, dfs 함수 수정

try 3) WA
원인: 참가자가 1명이 남았을 때 게임이 즉시 종료되어야 한다.
해결: dfs의 재귀 중단 조건 수정

try 4) WA
원인: DP 메모이제이션.
    '낮에 1번 참가자가 죽고 밤에 0번 참가자가 죽은 경우'와
    '낮에 0번 참가자가 죽고 밤에 1번 참가자가 죽은 경우'를 동일한 것으로 다루는 오류
"""
from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    scores = list(map(int, input().split()))
    R = [list(map(int, input().split())) for _ in range(N)]
    mafia = int(input())
    return N, scores, R, mafia


def solution(N: int, scores: List[int], R: List[List[int]], mafia: int) -> int:
    def dfs(bit: int, alive_cnt: int) -> int:
        """
        :param bit: 죽은 사람들의 번호가 저장된 비트마스크
        :param alive_cnt: 생존자의 수
        :return: 현 시점에서 지날 수 있는 최대의 밤의 수
        """
        if alive_cnt == 1:  # 생존자가 1명이면 더 이상의 밤은 없다
            return 0

        ret = 0
        if alive_cnt & 1:   # 생존자의 수가 홀수인 경우
            day_killed = max((person for person in range(N) if (1 << person) & ~bit),
                             key=lambda x: scores[x])
            if day_killed != mafia: # 낮에 죽는 사람이 은진이가 아닌 경우
                ret = dfs((1 << day_killed) | bit, alive_cnt - 1)
        else:               # 생존자의 수가 짝수인 경우
            for night_killed in range(N):
                if (1 << night_killed) & ~bit and night_killed != mafia:
                    for person, change in enumerate(R[night_killed]):
                        scores[person] += change
                    ret = max(ret, 1 + dfs((1 << night_killed) | bit, alive_cnt - 1))
                    for person, change in enumerate(R[night_killed]):
                        scores[person] -= change
        return ret
    return dfs(0, N)


if __name__ == '__main__':
    print(solution(*read_input()))
