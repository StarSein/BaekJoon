"""
[1차 채점] WA.
시작 시간의 마지노선을 기준으로 정렬을 한 것이 로직 에러의 원인이었다.
언뜻 보기에 시작 시간의 마지노선과 마감 시간이 의미하는 바는 같지만,
정렬을 했을 때 두 기준의 의미는 다르다.
마감 시간이 빠르다는 것은 '시작 시간의 마지노선 + 작업 소요 시간'이 빠르다는 것이고
그 작업을 최대한 늑장부리며 시작했을 때 다음 작업 수행에 미치는 악영향이 적다는 의미이다.
"""


import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def is_able(time: int) -> bool:
        cur_time = time
        i = 0
        while i < n and cur_time <= tasks[i][0] - tasks[i][1]:
            cur_time += tasks[i][1]
            i += 1
        return i == n

    n = int(input())
    tasks = []
    for i in range(n):
        t, s = map(int, input().split())
        tasks.append((s, t))
    tasks.sort()
    MAX_TIME = int(1e6)
    left, right = 0, MAX_TIME - 1
    answer = -1
    while left <= right:
        mid = (left + right) // 2
        if is_able(mid):
            answer = mid
            left = mid + 1
        else:
            right = mid - 1
    print(answer)


if __name__ == '__main__':
    main()
