def solution(X_list, Y_list):
    cnt_xOdd, cnt_yEven = 0, 0
    for i in range(len(X_list)):
        if X_list[i] % 2 != 0:
            cnt_xOdd += 1
    for i in range(len(Y_list)):
        if Y_list[i] % 2 == 0:
            cnt_yEven += 1
    sol = abs(cnt_xOdd - cnt_yEven)
    return sol


if __name__ == '__main__':
    N = int(input())
    sol_list = []
    while N:
        X_list = list(map(int, input().split()))
        Y_list = list(map(int, input().split()))
        sol = solution(X_list, Y_list)
        sol_list.append(str(sol))
        N = int(input())
    print('\n'.join(sol_list))