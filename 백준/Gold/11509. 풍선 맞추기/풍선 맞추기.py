"""
'자기보다 왼쪽에서 다른 풍선을 터뜨리고 내려오는 화살이 없는' 풍선은 꼭 겨냥해서 화살을 추가 소모 해야 한다.
탐욕법으로 접근할 수 있는 것이다.
모든 화살의 이동을 일일히 구현할 경우 시간 복잡도는 O(N^2)
이동하는 화살의 높이를 set에서 관리하면 어떨까?
set에 같은 높이의 화살이 있으면 굳이 화살을 추가하지 않고, 해당 높이를 1만큼 차감하기만 하면 된다.
이 방식을 따르면 시간 복잡도는 O(N)

[1차 채점]
WA. 같은 높이에 있는 화살이 여러 개인 경우를 간과했다.
ex) 4 4 4 3 3 3 - Answer:3, Output:5

set이 아니라 dict를 사용해서 개수 정보까지 저장하자.
"""
import sys
from collections import defaultdict


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    h_list = list(map(int, input().split()))

    height_cnt = defaultdict(int)
    cnt_arrow = 0
    for height in h_list:
        if height_cnt[height] == 0:
            cnt_arrow += 1
            height_cnt[height-1] += 1
        else:
            height_cnt[height] -= 1
            height_cnt[height-1] += 1
    print(cnt_arrow)


if __name__ == '__main__':
    main()
