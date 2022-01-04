def solution(N, damaged_list, spare_list):
    # num_list : [팀 i]가 가진 카약의 개수를 담은 배열 (i = 1 ~ N)
    num_list = [1] * (N+1)
    for team_damaged in damaged_list:   # 카약이 파손된 팀은 카약의 개수를 0으로 낮춘다.
        num_list[team_damaged] -= 1
    for team_spare in spare_list:       # 여분의 카약을 챙겨온 팀은 카약의 개수를 2로 늘린다.
        num_list[team_spare] += 1

    # 탐색 대상의 카약 수가 2이고, 인접한 팀의 카약 수가 0일 때
    # lendKayak 함수를 호출해서 카약 대여 처리
    i = 1                                           # [팀 1]부터 탐색 및 처리 시작
    if num_list[i] == 2 and num_list[i+1] == 0:     # [팀 1]의 경우 [팀 2]에게만 빌려줄 수 있다.
        lendKayak(num_list, i+1, i)
    i += 1
    while i < N:
        if num_list[i] == 2 and num_list[i-1] == 0:
            lendKayak(num_list, i-1, i)
        elif num_list[i] == 2 and num_list[i+1] == 0:
            lendKayak(num_list, i+1, i)
        i += 1
    if num_list[i] == 2 and num_list[i-1] == 0:     # [팀 N]의 경우 [팀 N-1]에게만 빌려줄 수 있다.
        lendKayak(num_list, i-1, i)

    # 대회에 출전할 수 없는 팀의 최솟값 = num_list 에서 값이 0인 항목의 개수
    numOfDisabled = num_list.count(0)
    return numOfDisabled


def lendKayak(num_list, team_damaged, team_spare):
    num_list[team_damaged] += 1
    num_list[team_spare] -= 1


if __name__ == '__main__':
    N, S, R = map(int, input().split())
    damaged_list = list(map(int, input().split()))
    spare_list = list(map(int, input().split()))

    sol = solution(N, damaged_list, spare_list)    # S, R은 문제풀이에 필요없는 변수이므로 호출하지 않는다.
    print(sol)