"""
try1) 완전 탐색으로 (a, b)를 찾으면 된다.
-> -100 <= a, b <= 100 으로 한정할 수가 없다.
    반례) num = [1, 0] a = 1e9, b = -1e9

try2) 연립 일차방정식의 관점에서 접근.
-> WA. 연산 '//'를 사용해서 a와 b가 정수로 계산되지 않을 때를 예외 처리하지 않음.
반례) N = 3, nums = [2, 0, 9] , 정답: B, 출력: 0
   해결법1) divmod 메소드 사용 -> mod != 0 이면 a, b가 정수가 아니므로 B
   해결법2) line 33의 반복문을 range(0, N-1) 범위에서 실행함
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def solution() -> str:
    N = int(input())
    nums = list(map(int, input().split()))

    if N == 1:
        return 'A'
    else:
        if nums[0] == nums[1]:
            if nums.count(nums[0]) == len(nums):
                return str(nums[0])
            else:
                return 'B'
        elif N == 2:
            return 'A'
        else:
            a = (nums[2] - nums[1]) // (nums[1] - nums[0])
            b = (nums[1] ** 2 - nums[0] * nums[2]) // (nums[1] - nums[0])
            if all(nums[i + 1] == a * nums[i] + b for i in range(N - 1)):
                ret = a * nums[N - 1] + b
                return str(ret)
            else:
                return 'B'


if __name__ == '__main__':
    print(solution())
