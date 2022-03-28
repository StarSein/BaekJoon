'''
입력으로 들어오는 "부품 닉네임"을 튜플(부품, 닉네임) 형태로 딕셔너리에 저장하자.
    key: tuple, value: 정수로 변환된 빌린 시각
만약 딕셔너리에 이미 있는 키값일 경우 value와 빌린 시각의 차를 바탕으로 벌금을 계산하고,
닉네임별로 키가 할당된 다른 딕셔너리에 벌금을 추가하자.
    key: 닉네임, value: 총 벌금
그 다음 딕셔너리에서 해당 키값을 제거하자.

[채점 1차시]
Wrong Answer. 부품을 반납하지 않은 사람은 없댔는데?

[채점 2차시]
Wrong Answer. 반납한 (부품, 닉네임)을 딕셔너리에서 제거하는 작업을 fee > 0일 때만 하고 있어서 로직 에러가 발생했다.
'''


import sys
import datetime as dt
from collections import defaultdict


def input():
    return sys.stdin.readline().rstrip()


def main():
    STD_DATE = dt.date.fromisoformat("2021-01-01")

    def date_str_to_int(date_str: str) -> int:
        date_delta = dt.date.fromisoformat(date_str) - STD_DATE
        return date_delta.days

    def time_str_to_int(time_str: str) -> int:
        h, m = map(int, time_str.split(':'))
        return 60 * h + m

    n, l, f = input().split()
    n, f = int(n), int(f)
    rental_period = 1440 * int(l[:3]) + 60 * int(l[4:6]) + int(l[7:])
    rental_times = dict()
    fees = defaultdict(int)
    for log in range(n):
        date, time, part, nickname = input().split()
        date_int = date_str_to_int(date)
        time_int = time_str_to_int(time)
        key_tuple = (part, nickname)
        if key_tuple not in rental_times:
            rental_times[key_tuple] = 1440 * date_int + time_int
        else:
            usage_period = 1440 * date_int + time_int - rental_times[key_tuple]
            fee = max(f * (usage_period - rental_period), 0)
            rental_times.pop(key_tuple)
            if fee > 0:
                nickname = key_tuple[1]
                fees[nickname] += fee
    res_list = sorted(fees.items())
    if res_list:
        for res in res_list:
            print(*res)
    else:
        print(-1)


if __name__ == '__main__':
    main()
