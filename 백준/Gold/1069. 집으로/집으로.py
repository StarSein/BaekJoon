"""
(x,y)에서 (0,0) 으로 일직선으로 가는 것이 최적이다.
1초당 이동 거리(걷기: 1, 점프: D/T)를 비교해서 더 긴 것을 사용하면 된다.
단 (0,0)까지의 거리가 D 미만일 때에는 다른 방식으로 선택해야 한다.
    걷기의 추가 소요 시간 = 거리
    점프의 추가 소요 시간 = T + (D - 거리)
걷기 or 점프를 다르게 결정할 그 시점까지 while 문으로 처리해도 시간 복잡도가 충분하지만,
계산식을 세우는 게 더 간결하겠다.

(0,0)까지의 거리가 D 미만일 때의 거리는 (LEN % D) 이다.
총 거리를 LEN 이라고 할 때,
cost1 = (LEN - LEN % D) / max(1, D / T)
cost2 = min(LEN % D, T + D - LEN % D)
ans = cost1 + cost2

[예제 4, 6] 이 나오지 않는다.
(x,y)에서 (0,0) 까지 일직선으로 가는 것이 최적이라는 전제에 오류가 있다.
아폴로니우스의 원을 생각하면, 남은 거리가 2 * D 미만인 시점에
(cx,cy)을 중심으로 하는 반지름 D의 원 1과
(0,0)을 중심으로 하는 반지름 D의 원 2의 두 교점 중 하나를 M이라고 하면,
(cx,cy) -> M -> (0,0) 두 번의 점프로 이동이 가능하다.
따라서
cost3 = (LEN - LEN % (2 * D)) / max(1, D / T)
cost4 = min(LEN % (2 * D), 2 * T)
ans = min(cost1 + cost2, cost3 + cost4)

try 1) WA.
원인: 코드를 덜 수정함.

try 2) WA.
원인: cost3 이 최적이 아니다.
cost4 = min(2 * D, 2 * T)로 세워도 된다.
식이 복잡해지므로, 그냥 while 문으로 처리하자.
"""
from sys import stdin
from math import sqrt, ceil


input = lambda: stdin.readline().rstrip()


def solution(X: int, Y: int, D: int, T: int) -> float:
    LEN = sqrt(X ** 2 + Y ** 2)

    cost1 = (LEN - LEN % D) / max(1.0, D / T)
    cost2 = min(LEN % D, T + D - LEN % D)

    dist = LEN
    cost3 = 0
    if D / T > 1.0:
        step = D
        t = T
    else:
        step = 1
        t = 1
    double_jump = 2 * D
    while dist > double_jump:
        dist -= step
        cost3 += t

    cost4 = min(dist, 2 * T)

    ans = min(cost1 + cost2, cost3 + cost4)
    return ans


if __name__ == '__main__':
    print(solution(*map(int, input().split())))
