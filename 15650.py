import sys


N, M = map(int, sys.stdin.readline().split())

num_list = [i + 1 for i in range(N)]    # index 에 숫자와 그 숫자가 수열을 구성하고 있는지의 여부를 대응시킨다.
check_list = [False] * N                # 따라서 숫자를 넣을지 말지 결정할 때 index 를 매개로 하여 현재 상태를 확인 가능하다.
arr = []                                # 수열이 담길 배열 혹은 스택 arr


def dfs(count):                         # 재귀함수 dfs 의 중첩단계와 같은 변수 count 를 매개변수로 하는 깊이 우선 탐색 함수

    if count == M:                      # count 가 0 이라면, 현재 배열 arr 에는 M 개의 원소가 있음
        print(*arr)                     # 따라서 asterisk 로 배열 arr 을 unpacking 하여 출력함
        return                          # 함수를 종료함 (현재의 깊이 우선 탐색의 한 줄기가 더 이상 뻗어나가지 않고, 다음 줄기로 넘어감)

    for i in range(N):                  # 배열 arr 의 새 원소로 넣을 수 있는 숫자에 대해서만 line 21~27 의 코드를 실행함
        if check_list[i]:
            continue

        check_list[i] = True            # i 라는 index 에 대응되는 숫자가 현재의 탐색 줄기에서 더 이상 사용되지 않도록 하고,
        arr.append(num_list[i])         # i 라는 index 에 대응되는 숫자를 배열 arr 에 추가함 ( 이는 배열 arr 에서 count 번째 원소)
        dfs(count + 1)                  # 현재의 배열 arr 을 유지한 채 현재의 깊이 우선 탐색 줄기를 한 칸 더 뻗어나감
        arr.pop()                       # 다음 loop 에서 배열 arr 의 count 번째 항목으로 그 다음 숫자가 할당될 수 있도록 지금 숫자를 삭제함

        for j in range(i + 1, N):       # i 라는 index 에 대응되는 숫자가 배열 arr 의 마지막 항목으로 존재했는데,
            check_list[j] = False       # 그 이후의 숫자들이 다음 깊이 우선 탐색에서 사용될 수 있도록, 그 숫자들과 대응되는 bool 을 False 로 할당함
                                       

dfs(0)                                  # 배열 arr 의 0번째 항목부터 너비 우선 탐색, 그런데 이제 백트래킹을 곁들인... 을 시작함
