"""
경우의 수가 최대 2^50이라서 완전 탐색이나 백트래킹은 안 될 듯하다.

정렬을 하면 O(N^3)의 시간 복잡도로 풀 수 있겠다.
x <= y <= z 일 때, y + z > x, x + z > y 임은 자명하다.
x + y > z인지 여부만 체크하면 된다.
이 조건을 만족하지 않는 z가 나올 때까지, z들을 포함하면 된다.

시간적으로 최적화하기 위해, 중복을 제거한 수열과 각 수의 개수를 저장해놓은 멀티셋을 사용하자.
중복을 제거하려니 오히려 엣지케이스가 많아진다.
그냥 주어진 수열을 그대로 사용하자.
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    nums = sorted(list(map(int, input().split())))

    if n < 3:
        print(n)
        return

    nums_size = len(nums)
    max_length = 1
    for x in range(nums_size):
        for y in range(x + 1, nums_size):
            two_sum = nums[x] + nums[y]
            z_list = []
            z = y + 1
            while z < nums_size and two_sum > nums[z]:
                z_list.append(nums[z])
                z += 1
            curr_length = 2 + len(z_list)
            max_length = max(max_length, curr_length)

    print(max_length)


if __name__ == '__main__':
    main()
