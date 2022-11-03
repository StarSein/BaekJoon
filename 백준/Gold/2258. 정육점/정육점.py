"""
어떤 덩어리보다 싼 고기들을 얼마든지 덤으로 얻을 수 있는 조건 하에
원하는 양의 고기를 최소 비용으로 구매했다면,

비용 = (어떤 덩어리의 가격) * k 이다. (k >= 1)

(A 덩어리의 가격) < (B 덩어리의 가격) 이라고 할 때,
두 덩어리의 가격을 모두 지불하는 경우는 없기 때문이다.
오직 (A 덩어리의 가격) == (B 덩어리의 가격) 일 때만
두 덩어리의 가격을 모두 지불하게 된다.

그렇다면 (덩어리의 무게의 합, 덩어리의 가격의 합)의 순서쌍은 N개 존재한다.
1번의 반복문으로 이 순서쌍들 중 정답을 찾으면 된다.

크기 N의 순서쌍 배열 만들기
1. 고기를 정렬한다 (정렬 우선순위 1: 가격이 낮은 순, 2: 무게가 높은 순)
2. 무게의 누적합을 갱신하며 누적합을 순서쌍에 넣는데,
    이전 덩어리보다 가격이 높은 덩어리의 경우 해당 덩어리의 가격만 순서쌍에 넣고,
    이전 덩어리와 가격이 같은 덩어리의 경우 이전 덩어리의 가격도 더해서 순서쌍에 넣는다.

try 1) WA
원인: ws > m 조건 분기
해결: ws >= m 으로 수정

try 2) WA
원인: prev_price 를 갱신하지 않았다

try 3) WA
원인: INF의 값을 옮겨적는 데 오타가 있었다.
해결: 그냥 마음 편하게 sys.maxsize ( = 2^63 - 1 )를 사용하자
"""
import sys
from typing import Tuple, List


input = lambda: sys.stdin.readline().rstrip()


def solution(n: int, m: int, meats: List[Tuple[int, int]]) -> int:
    meats.sort(key=lambda x: (x[1], -x[0]))

    case_list = []
    weight_sum = 0
    price_sum = 0
    prev_price = -1
    for weight, price in meats:
        weight_sum += weight
        if price > prev_price:
            price_sum = price
        else:
            price_sum += price
        case_list.append((weight_sum, price_sum))
        prev_price = price

    INF = sys.maxsize
    best_price = INF
    for ws, ps in case_list:
        if ws >= m and ps < best_price:
            best_price = ps

    return best_price if best_price != INF else -1


if __name__ == '__main__':
    N, M = map(int, input().split())
    print(solution(N, M, [tuple(map(int, input().split())) for _ in range(N)]))