"""
[2차원 DP]
dp[row][col] = 1번부터 col번까지의 선수를 row에 배치했을 때 능력치 합의 최댓값
row는 1에서 11까지의 비트 마스크.

[TC별 시간 복잡도]
1. 완전 탐색: 경우의 수 = 11! = 39,916,800
2. DP: 2차원 배열의 크기 = (2**11) * 11 * 11 = 2048 * 121 = 247808

완전 탐색의 경우 테케의 개수가 1개여도 시간 초과가 난다.
DP의 경우 테케의 개수가 100개 이하라면 충분하다.
위처럼 2차원 DP를 하면
'1번부터 col번까지의 선수를 row에 배치하는 모든 경우의 수'를 고려하지 않고,
'1번부터 col번까지의 선수를 row에 배치하는 최적의 경우가 뭔지는 모르겠으나 최적일 때의 능력치 합'만 저장하므로,
탐색하는 경우의 수가 상당히 감소하는 셈이다.

try 1) TLE
원인: 각 선수는 능력치가 0인 포지션에 배치될 수 없다는 조건을 간과했다.

try 2) WA
원인: dp[bit][pos]의 값이 dp[bit][pos + 1]로 연결되지 않는다.
해결: 그냥 모든 bit마다 이전 pos의 값을 max 함수의 인자로 넣어주자.
     (pos_bit | bit) 과 충돌할 수 있으니까.

try 3) WA
try 2의 원인과 해결은 아무 의미가 없고 오히려 모순이다.
원인: pos_bit이 bit에 포함되는지 여부만 따지면 되는 줄 알았다. 어차피 dp테이블에는 max값만 남으니까.
    하지만 bit에서 1의 개수가 현재 추가할 선수의 번호에 맞지 않으면 모순이다.
예컨대 4명의 선수와 4개의 포지션만 있다고 할 때
1
1 1 7 5
0 0 1 0
0 1 0 0
2 0 0 4
이 경우 정답인 9가 잘 나오네..?
아무튼 이 부분이 문제의 원인이다.
해결: bin 메소드와 count 메소드를 이용해 비트에서 1의 개수를 셀 수도 있겠지만,
    dp테이블에서 방문하지 않은 지점의 값을 -1로 구분하는 식으로 구현하는 게 더 효율적이다.

"""
from sys import stdin
from typing import Tuple


input = lambda: stdin.readline().rstrip()


def solution(stats: Tuple[Tuple[int, ...]]) -> int:
    dp = [[-1] * (NUM_POS + 1) for _ in range(SIZE)]
    dp[0][0] = 0
    for i, stat_tuple in enumerate(stats, start=1):
        for pos, stat in enumerate(stat_tuple):
            if stat:
                pos_bit = 1 << pos
                for bit in range(SIZE):
                    if dp[bit][i - 1] != -1 and pos_bit & ~bit:
                        dp[pos_bit | bit][i] = max(dp[pos_bit | bit][i], dp[bit][i - 1] + stat)

    return dp[SIZE - 1][NUM_POS]


if __name__ == '__main__':
    NUM_POS = 11
    SIZE = 1 << NUM_POS

    C = int(input())
    for _ in range(C):
        S = tuple(tuple(map(int, input().split())) for _ in range(NUM_POS))
        print(solution(S))
        