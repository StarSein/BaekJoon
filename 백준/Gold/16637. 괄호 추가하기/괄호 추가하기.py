"""
2 * 연산자 개수 크기의 DP 배열 2개를 만든다. min_dp, max_dp
dp[i][0] = i번째 연산에 괄호를 추가했을 때 결과값
dp[i][1] = i번째 연산에 괄호를 추가하지 않았을 때 결과값

dp[i][0] = min(dp[i-2]) 연산[i-1] (num[i] 연산[i] num[i+1])
dp[i][1] = min(dp[i-1]) 연산[i]  num[i+1]

덧셈
max = max + num
min = min + num
뺄셈
max = max - num
min = min - num
곱셈
max = max * num (if num > 0), min * num (else)
min = min * num , min * num (if num > 0) , max * min (else)
"""
import sys
from copy import deepcopy


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    if n == 1:
        print(input())
        return
    num_list = []
    opr_list = []
    for char in input():
        if char.isdigit():
            num_list.append(int(char))
        else:
            opr_list.append(char)

    min_dp = [[num_list[0]] * 2 for _ in range(len(opr_list) + 1)]
    if opr_list[0] == '+':
        min_dp[1] = [num_list[0] + num_list[1]] * 2
    elif opr_list[0] == '-':
        min_dp[1] = [num_list[0] - num_list[1]] * 2
    else:
        min_dp[1] = [num_list[0] * num_list[1]] * 2
    max_dp = deepcopy(min_dp)

    for i in range(1, len(opr_list)):
        if opr_list[i] == '+':
            max_dp[i+1][1] = max(max_dp[i]) + num_list[i+1]
            min_dp[i+1][1] = min(min_dp[i]) + num_list[i+1]
            right = num_list[i] + num_list[i+1]
        elif opr_list[i] == '-':
            max_dp[i+1][1] = max(max_dp[i]) - num_list[i+1]
            min_dp[i+1][1] = min(min_dp[i]) - num_list[i+1]
            right = num_list[i] - num_list[i+1]
        else:
            max_dp[i+1][1] = max(max(max_dp[i]) * num_list[i+1], min(min_dp[i]) * num_list[i+1])
            min_dp[i+1][1] = min(max(max_dp[i]) * num_list[i+1], min(min_dp[i]) * num_list[i+1])
            right = num_list[i] * num_list[i+1]

        if opr_list[i-1] == '+':
            max_dp[i+1][0] = max(max_dp[i-1]) + right
            min_dp[i+1][0] = min(min_dp[i-1]) + right
        elif opr_list[i-1] == '-':
            max_dp[i+1][0] = max(max_dp[i-1]) - right
            min_dp[i+1][0] = min(min_dp[i-1]) - right
        else:
            max_dp[i+1][0] = max(max(max_dp[i-1]) * right, min(min_dp[i-1]) * right)
            min_dp[i+1][0] = min(max(max_dp[i-1]) * right, min(min_dp[i-1]) * right)

    print(max(max_dp[-1]))


if __name__ == '__main__':
    main()
