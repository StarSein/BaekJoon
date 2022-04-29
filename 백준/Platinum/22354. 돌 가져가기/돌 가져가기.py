"""
같은 색의 돌이 연속한 구간에서는 구간 내의 최댓값을 선택해야 한다.
예제 1번: WBBWBWBB 는 WBWBWB 로 축약된다.
축약된 문자열에서 가장 왼쪽과 오른쪽을 제외한 문자의 개수가 n이라고 하면
항상 (n + 1) // 2 개만큼의 돌을 가져올 수 있다.
즉 이 문제는 n개의 구간 중에서 (n + 1) // 2 개의 구간을 선택하는 문제다.
그리디 알고리즘으로 풀 수 있는 것이다.
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    s = input()
    scores = list(map(int, input().split()))
    curr_color = s[0]
    segments = [scores[0]]
    for i in range(1, n):
        if s[i] == curr_color:
            segments[-1] = max(segments[-1], scores[i])
        else:
            curr_color = s[i]
            segments.append(scores[i])
    seg_size = len(segments)
    print(sum(sorted(segments[1:-1], reverse=True)[:(seg_size - 1) // 2]))


if __name__ == '__main__':
    main()
