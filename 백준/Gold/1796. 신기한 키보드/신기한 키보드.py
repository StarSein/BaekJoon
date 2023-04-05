from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    return input()


def solution(S: str) -> int:

    def dfs(depth: int, cur_pos: int, push_cnt: int) -> int:
        if depth == len(chars):
            return push_cnt

        idx = chars[depth]
        if cnt[idx] == 1:
            return dfs(depth + 1, lp[idx], push_cnt + abs(cur_pos - lp[idx]) + 1)
        else:
            return min(
                dfs(depth + 1, lp[idx], push_cnt + abs(cur_pos - rp[idx]) + rp[idx] - lp[idx] + cnt[idx]),
                dfs(depth + 1, rp[idx], push_cnt + abs(cur_pos - lp[idx]) + rp[idx] - lp[idx] + cnt[idx])
            )

    lp = [51 for _ in range(26)]
    rp = [-1 for _ in range(26)]
    cnt = [0 for _ in range(26)]
    for pos, char in enumerate(S):
        idx = ord(char) - ord('a')
        lp[idx] = min(lp[idx], pos)
        rp[idx] = max(rp[idx], pos)
        cnt[idx] += 1

    chars = [i for i in range(26) if cnt[i] > 0]

    return dfs(0, 0, 0)


if __name__ == '__main__':
    print(solution(read_input()))
