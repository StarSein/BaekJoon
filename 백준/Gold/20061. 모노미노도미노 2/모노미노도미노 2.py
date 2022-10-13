from sys import stdin
from typing import Tuple


input = lambda: stdin.readline().rstrip()

"""
초록색 보드와 파란색 보드를 각각 크기가 4인 green[], blue[] 배열로 관리하자.
각 배열에는 0 <= val < 2**6 의 값을 저장하여 비트열로 활용한다. 비트마스킹
1. 블록 낙하
    1) 1x1 블록 : green[y], blue[x]의 2~5까지의 비트를 순서대로 확인. On 상태의 비트가 발견되면 그것보다 1만큼 작은 비트를 켠다.
    2) 1x2 블록 :
        - green: [y] [y+1]의 2~5까지의 비트를 순서대로 확인. On 상태의 비트가 발견되면 그것보다 1만큼 작은 비트를 켠다.
        - blue: [x]의 2~5까지의 비트를 순서대로 확인. On 상태의 비트가 발견되면 그것보다 1,2만큼 작은 비트를 켠다.
    3) 2x1 블록 : 2)에서 green과 blue에 했던 것을 반대로 수행.

2. 행이나 열이 타일로 가득찬 경우 처리
    - green, blue 각각의 모든 인덱스에서 5~2까지의 비트를 순서대로 확인. 모든 인덱스가 On인 비트가 발견되면 1점 획득.
    - 가득찬 부분을 비우는 작업: 비워지는 비트를 x라고 하면, x를 기준으로 비트열을 윗부분과 아랫부분으로 나눈다. 
                          아랫부분은 그대로 두고, 윗부분은 오른쪽 시프트 연산 처리한다.
3. 연한 부분의 타일 처리
    - green, blue 각각의 모든 인덱스에서 0~1까지의 비트를 확인.
      단 하나의 인덱스라도 On인 비트의 개수만큼 비트열을 오른쪽 시프트 연산 처리.

4. 마지막에 타일이 있는 칸의 개수: 전체에서 On 비트의 개수


try1) WA.
질답게 반례: https://www.acmicpc.net/board/view/93335
5
3 2 0
3 2 1
3 2 2
2 0 0
3 2 3
정답: 2, 12
출력: 1, 16
원인: 두 줄이 한 번에 사라져서 두 칸에 함께 내려가는 부분에서 구현 미스
해결: 꽉 찬 라인 체크 및 비움 처리를 5~2비트 순서로 시행해서 발생한 오류. 2~5비트 순서로 시행하니 해결.

try2) WA.
질답게 반례: https://www.acmicpc.net/board/view/67568
8
3 1 0
3 0 1
1 2 1
1 0 0
3 2 1
3 0 1
1 3 1
2 2 1
답 : 1, 14
출력: 1, 13
원인: 사라지는 줄을 bit라고 할 때 비트열의 윗부분을 [0, bit)로 두어야 하는데, [2, bit)로 두어서 오류가 발생함.  
"""

def solution(n: int, blocks: Tuple[Tuple[int, int, int]]) -> Tuple[int, int]:
    SIZE = 4
    green = [0] * SIZE
    blue = [0] * SIZE
    strong_bit_list = [1 << 2, 1 << 3, 1 << 4, 1 << 5]
    weak_bit_list = [1 << 0, 1 << 1]

    ans = 0
    for t, x, y in blocks:
        if t == 1:
            flag = True
            for bit in strong_bit_list:
                if green[y] & bit:
                    green[y] |= bit >> 1
                    flag = False
                    break
            if flag:
                green[y] |= 1 << 5

            flag = True
            for bit in strong_bit_list:
                if blue[x] & bit:
                    blue[x] |= bit >> 1
                    flag = False
                    break
            if flag:
                blue[x] |= 1 << 5
        elif t == 2:
            flag = True
            for bit in strong_bit_list:
                if green[y] & bit or green[y + 1] & bit:
                    green[y] |= bit >> 1
                    green[y + 1] |= bit >> 1
                    flag = False
                    break
            if flag:
                green[y] |= 1 << 5
                green[y + 1] |= 1 << 5
            flag = True
            for bit in strong_bit_list:
                if blue[x] & bit:
                    blue[x] |= (bit >> 2) | (bit >> 1)
                    flag = False
                    break
            if flag:
                blue[x] |= (1 << 4) | (1 << 5)
        else:
            flag = True
            for bit in strong_bit_list:
                if blue[x] & bit or blue[x + 1] & bit:
                    blue[x] |= bit >> 1
                    blue[x + 1] |= bit >> 1
                    flag = False
                    break
            if flag:
                blue[x] |= 1 << 5
                blue[x + 1] |= 1 << 5

            flag = True
            for bit in strong_bit_list:
                if green[y] & bit:
                    green[y] |= (bit >> 2) | (bit >> 1)
                    flag = False
                    break
            if flag:
                green[y] |= (1 << 4) | (1 << 5)

        for bit in strong_bit_list:
            on_cnt = 0
            for idx in range(SIZE):
                if green[idx] & bit:
                    on_cnt += 1
            if on_cnt == SIZE:
                ans += 1
                for idx in range(SIZE):
                    upper_mask = 0
                    b = bit << 1
                    while b <= (1 << 5):
                        if green[idx] & b:
                            upper_mask |= b
                        b <<= 1
                    lower_mask = 0
                    b = bit >> 1
                    while b >= (1 << 0):
                        if green[idx] & b:
                            lower_mask |= b
                        b >>= 1
                    green[idx] = upper_mask | (lower_mask << 1)

            on_cnt = 0
            for idx in range(SIZE):
                if blue[idx] & bit:
                    on_cnt += 1
            if on_cnt == SIZE:
                ans += 1
                for idx in range(SIZE):
                    upper_mask = 0
                    b = bit << 1
                    while b <= (1 << 5):
                        if blue[idx] & b:
                            upper_mask |= b
                        b <<= 1
                    lower_mask = 0
                    b = bit >> 1
                    while b >= (1 << 0):
                        if blue[idx] & b:
                            lower_mask |= b
                        b >>= 1
                    blue[idx] = upper_mask | (lower_mask << 1)

        on_cnt = 0
        for bit in weak_bit_list:
            for idx in range(SIZE):
                if green[idx] & bit:
                    on_cnt += 1
                    break
        for idx in range(SIZE):
            green[idx] <<= on_cnt
            green[idx] &= ~((1 << 6) | (1 << 7))

        on_cnt = 0
        for bit in weak_bit_list:
            for idx in range(SIZE):
                if blue[idx] & bit:
                    on_cnt += 1
                    break
        for idx in range(SIZE):
            blue[idx] <<= on_cnt
            blue[idx] &= ~((1 << 6) | (1 << 7))

    tile_cnt = 0
    for idx in range(SIZE):
        for bit in strong_bit_list:
            if green[idx] & bit:
                tile_cnt += 1
            if blue[idx] & bit:
                tile_cnt += 1


    return ans, tile_cnt


if __name__ == '__main__':
    N = int(input())
    block_tuple = tuple(tuple(map(int, input().split())) for _ in range(N))
    print(*solution(N, block_tuple), sep='\n')

