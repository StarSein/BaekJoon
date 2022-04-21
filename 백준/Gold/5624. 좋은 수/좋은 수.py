"""
정렬을 할 수 없으므로, 이분 탐색이나 투 포인터는 사용할 수 없다.
N <= 5000이니까 O(N^2)의 시간 복잡도를 갖는 알고리즘으로 풀어야 한다.

                [이 풀이 X]
                num_set = set()
                from i = 1 to N-1 do
                    num_set += dict({A[i] - A[k]: 1} (from k = 0 to i-1))

                cnt = 0
                from i = 0 to N-2 do
                    num_set -= dict({A[i] - A[k]: 1} (from k = 0 to i-1))
                    from j = 0 to i do
                        if A[i] + A[j] in num_set
                            cnt += 1
                            break

                num_set이 multiset으로 구현되어야 하니까, Counter 클래스를 사용하자.
                중복 방지를 해야 한다.

중복을 간편하게 방지하려면
cnt = 0
num_set = set()
from i = 1 to N do
    num_set.update(set(A[i-1] + A[k] (from k = 0 to i-1))
    from j = 0 to i-1 do
        if A[i] - A[j] in num_set:
            cnt += 1
            break
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    seq = list(map(int, input().split()))

    cnt_good_num = 0
    two_sum_set = set()
    for i in range(1, n):
        two_sum_set.update(set(seq[i-1] + seq[k] for k in range(i)))
        if any((seq[i] - seq[j] in two_sum_set) for j in range(i)):
            cnt_good_num += 1
    print(cnt_good_num)


if __name__ == '__main__':
    main()
