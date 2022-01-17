import sys
from itertools import combinations

input = sys.stdin.readline
K = 3   # K: 아이스크림을 뽑는 개수


def solution() -> int:
    icecreams = list(range(1, n+1))                 # icecreams: 아이스크림들의 배열
    cnt_combs = 0                                   # cnt_combs: 가능한 선택 방법의 수
    for comb in combinations(icecreams, K):         # 아이스크림을 K개 뽑은 모든 케이스(comb)에 대해 iteration
        is_banned = False                           # is_banned: 해당 케이스를 선택 가능한지 여부(True 이면 불가능)
        for i in range(K):                              # K = 3인 경우, (ice0, ice1, ice2) 에 대해
            an_icecream = comb[i]                       # ice0과 ice1 / ice1과 ice2 / ice2와 ice0 /
            another = comb[(i+1) % K]                   # 위 3가지 아이스크림 사이의 관계 적절성 여부 확인
            if an_icecream in banned_combs[another]:    # 적절하지 않은 경우 해당 케이스는 선택 불가능
                is_banned = True
                break

        if not is_banned:                               # is_banned 가 False 일 때만 카운트
            cnt_combs += 1

    return cnt_combs


if __name__ == '__main__':
    n, m = map(int, input().split())
    banned_combs = [set() for _ in range(n + 1)]    # banned_combs: 인덱스 i에 대해, 'i번 아이스크림'과 섞어먹으면 안 되는 아이스크림의 집합을 담은 리스트
    for i in range(m):                              # 배열이 아닌 집합을 원소로 한 이유: line 16 의 탐색 연산을 더욱 빨리 하기 위해서
        numA, numB = map(int, input().split())
        banned_combs[numA].add(numB)
        banned_combs[numB].add(numA)
    sol = solution()
    print(sol)
