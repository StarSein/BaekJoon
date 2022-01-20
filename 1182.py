import sys


input = sys.stdin.readline
ASCII_A = 65

subseq = []          # 백트래킹 과정에서 부분수열에 포함된 원소의 인덱스를 담는 리스트
subseq_set = set()   # 중복 방지를 위한 자료구조 set 사용


def solution() -> int:
    backtrack(0, 0)                          # 0번 인덱스부터, 부분수열의 합의 초기값을 0으로 설정하고 백트래킹 시작
    return len(subseq_set)


def backtrack(pos: int, sum_subseq: int):    # pos: 수열에서 현재 위치(인덱스), sum_subseq: 부분수열의 합
    if sum_subseq == s and len(subseq) > 0:  # s = 0 인 경우, 길이가 0인 부분수열은 경우의 수에서 제외하기 위함
        subseq_set.add(''.join(subseq))      # set은 리스트를 hash할 수 없어, 원소로 갖지 못함. 따라서 문자열로 변환하여 set에 저장

    if pos < n:
        subseq.append(chr(ASCII_A + pos))    # 알파벳으로 변환하여 부분수열 스택에 저장: 단순히 숫자의 인덱스(pos)를 저장하면, 나중에 집합에서 [1, 2]와 [12]이 "12"로 중복처리됨 
        backtrack(pos + 1, sum_subseq + seq[pos])  # 부분수열에 해당 pos의 숫자를 포함하는 경우
        subseq.pop()
        backtrack(pos + 1, sum_subseq)             # 부분수열에 해당 pos의 숫자를 포함하지 않는 경우


if __name__ == '__main__':
    n, s = map(int, input().split())
    seq = list(map(int, input().split()))
    sol = solution()
    print(sol)
