import sys
from typing import List


input = sys.stdin.readline


def solution() -> List[str]:
    res = ['0'] * (n + 1)
    semester = 1
    while True:                                                 # study_list: 해당 학기에 이수할 과목(아직 배우지 않았으면서, 아직 듣지 않은 선수과목의 수가 0인 과목)
        study_list = [subject for subject in range(1, n + 1) if res[subject] == '0' and precede_cnts[subject] == 0]
        if len(study_list) == 0:                                # 해당 학기에 들을 과목이 없으면 반복문을 중단한다
            break

        for idx, subject in enumerate(study_list):              # 해당 학기에 듣는 과목에 대해 지금이 몇 번째 학기인지를 입력한다
            res[subject] = str(semester)

            for i, follow in enumerate(followers[subject]):     # 해당 학기에 듣는 과목의 후행과목에 대해, '아직 듣지 않은 선수과목의 수'를 1만큼 차감한다
                precede_cnts[follow] -= 1
        semester += 1                                           # 다음 학기로 넘어간다
    return res[1:]


if __name__ == '__main__':
    n, m = map(int, input().split())
    precede_cnts = [0] * (n + 1)                # precede_cnts: idx 번 과목의 선수과목의 개수를 담은 리스트
    followers = [[] for _ in range(n + 1)]      # followers: idx 번 과목이 선수과목인 과목들의 목록을 담은 리스트
    for i in range(m):
        a, b = map(int, input().split())
        followers[a].append(b)
        precede_cnts[b] += 1
    sol = solution()
    print(' '.join(sol))
