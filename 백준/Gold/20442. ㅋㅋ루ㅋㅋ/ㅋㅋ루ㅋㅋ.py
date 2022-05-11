"""
[1차 채점] WA.
양 끝의 K의 개수가 서로 같아야 한다는 점을 간과했다.
누적합을 사용해 구간 내의 K와 R의 개수를 저장하고
투 포인터로 R의 반복구간을 좁혀가며 최적의 'ㅋㅋ루ㅋㅋ'의 길이를 갱신하자.
"""

import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    string = input()
    pref_sum = [[0, 0] for _ in range(len(string) + 1)]
    K, R = 0, 1
    char_to_key = {'K': K, 'R': R}
    for idx, char in enumerate(string, start=1):
        pref_sum[idx][K] = pref_sum[idx-1][K]
        pref_sum[idx][R] = pref_sum[idx-1][R]
        pref_sum[idx][char_to_key.get(char)] += 1
        
    max_len = pref_sum[-1][R]
    lp, rp = 1, len(string)
    while lp + 1 < rp:
        num_left_k = pref_sum[lp][K]
        num_right_k = pref_sum[-1][K] - pref_sum[rp-1][K]
        num_mid_r = pref_sum[rp-1][R] - pref_sum[lp][R]
        if num_left_k == num_right_k and num_mid_r:
            cur_len = num_left_k + num_right_k + num_mid_r
            max_len = max(max_len, cur_len)
            lp += 1
        elif num_left_k < num_right_k:
            lp += 1
        else:
            rp -= 1
    print(max_len)


if __name__ == '__main__':
    main()
