"""
구사과: 오름차순으로 정렬된 리스트
큐브러버: 내림차순으로 정렬된 리스트


1. 같은 인덱스의 문자끼리 비교한다.
2. if 구사과의 문자가 더 빠른 경우에는 각자 제일 왼쪽의 물음표를 변경하면 된다.
   else 구사과의 문자가 더 느린 경우 (앞으로도 계속 더 느리게 된다.)
        남은 위치들의 절반 지점부터 구사과의 문자로 채워 간다.
        큐브러버의 문자는 그 절반 지점까지 왼쪽부터 채워 간다.

[1차 채점] WA.
구사과의 관점에서만 최적인 게임 방식이다.
큐브러버 역시 완전 정보 게임을 하고 있다는 것을 반영해야 한다.
그러면 2-else 경우에서
서로 가장 오른쪽의 위치를 선점하려고 하게 된다.
그 위치에 넣을 문자는 '이름'에 들어갈 문자들 중 우선순위가 가장 낮은 것부터

[2차 채점] WA.
같은 인덱스의 문자끼리'만' 비교하는 것이 로직 에러의 원인이었다.
"""
import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    koo = sorted(list(input()))
    cube = sorted(list(input()), reverse=True)

    koo_dq = deque([koo[i] for i in range((len(koo)+1) // 2)])
    cube_dq = deque([cube[i] for i in range(len(koo) // 2)])

    left, right = [], []
    for i in range(len(koo)):
        if i % 2 == 0:
            if len(cube_dq) == 0 or koo_dq[0] < cube_dq[0]:
                left.append(koo_dq.popleft())
            else:
                right.append(koo_dq.pop())
        else:
            if len(koo_dq) == 0 or koo_dq[0] < cube_dq[0]:
                left.append(cube_dq.popleft())
            else:
                right.append(cube_dq.pop())
    right.reverse()
    name = left + right
    print(''.join(name))


if __name__ == '__main__':
    main()
