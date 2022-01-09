import sys


input = sys.stdin.readline


def solution(n, post_list, pre_list):
    res_list = [0] * (n+1)
    semester = 1
    checked_subj_cnt = 0
    while checked_subj_cnt < n:
        subtracting_pre_list = []
        for subj in range(1, n+1):
            if pre_list[subj] == 0:
                res_list[subj] = semester
                pre_list[subj] -= 1
                checked_subj_cnt += 1
                for post in post_list[subj]:
                    subtracting_pre_list.append(post)
        for subj in subtracting_pre_list:
            pre_list[subj] -= 1
        semester += 1

    return res_list

                    
if __name__ == '__main__':
    n, m = map(int, input().split())
    post_list = [[] for _ in range(n+1)]
    pre_list = [0] * (n+1)
    for i in range(m):
        pre, post = map(int, input().split())
        post_list[pre].append(post)
        pre_list[post] += 1
    res_list = solution(n, post_list, pre_list)

    for subj in range(1, n+1):
        print(res_list[subj], end=' ')
