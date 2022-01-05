import sys

input = sys.stdin.readline
MAX_NUM = 10000


def solution(cnt_list):
    num_list = []
    for num in range(len(cnt_list)):
        for cnt in range(cnt_list[num]):
            num_list.append(str(num))
    sol = '\n'.join(num_list)
    return sol


if __name__ == '__main__':
    n = int(input())
    cnt_list = [0] * (MAX_NUM + 1)
    for i in range(n):
        num = int(input())
        cnt_list[num] += 1
    sol = solution(cnt_list)
    print(sol)