'''
탐욕법으로 해결할 수 있는 문제 같다.
배열의 어느 요소를 특정 값에 일치시키기 위한 최선의 방법은 bfs를 통해 구할 수 있다.
아 [두 배] 작업으로 인한 연산 횟수를 모든 요소가 공유하기 때문에 탐욕법으로는 풀 수 없겠다.

백트래킹으로 해결해보자.
    backtrack(cnt: int, pos: int)
    배열의 요소 중 B의 요소보다 큰 값이 발생할 경우 재귀 호출을 중단한다.
    중복 탐색을 방지하기 위해 +1 연산 후 호출하는 backtrack함수의 pos는 +1 처리를 한 요소의 인덱스
    *2 연산 후 호출하는 pos는 0 (다시 모든 인덱스에 대해서 +1 여부 선택이 가능하도록)

암만 생각해 봐도 시간 소모가 상당할 것 같다. 탐욕법이 해법이 아닐까?
연산의 순서는 중요치 않다. 횟수만 중요하므로,
일단 필요한 덧셈과 곱셈 연산의 개수만 구해놓으면, 최적의 방법으로 연산을 배치한다고 가정하면 된다.
총 연산의 횟수 = sum(덧셈의 횟수) + max(곱셈의 횟수)

곱셈을 최대한 활용하면서 덧셈의 횟수를 최소화해야 한다.
제일 마지막에 곱셈한 결과가 목표치와 같거나 1만큼 작아야 한다.
이같은 조건을 재귀적으로 적용한다면, 십진수를 이진수로 변환하는 과정에서의 연산과 유사한 형태가 보인다.
즉 십진수를 이진수로 변환한 값에서 1의 개수가 덧셈의 개수, 0의 개수가 곱셈의 개수다.

dp 배열을 만들어서 dp[num] = (덧셈의 개수, 곱셈의 개수)로 저장하고
    dp[num] = min(tup(dp[num//2][0] + num%2, dp[num//2][1] + 1), tup(dp[num-1][0] + 1, dp[num-1][1])
관계식을 이용하는 것도 방법이다.
'''

import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    b_list = list(map(int, input().split()))
    max_num = max(b_list)
    dp = list()
    dp.append((0, 0))
    for num in range(1, max_num + 1):
        dp.append(min((dp[num//2][0] + num % 2, dp[num//2][1] + 1), (dp[num-1][0] + 1, dp[num-1][1])))
    max_mult = 0
    sum_add = 0
    for num in b_list:
        max_mult = max(max_mult, dp[num][1])
        sum_add += dp[num][0]
    print(max_mult + sum_add)


if __name__ == '__main__':
    main()
