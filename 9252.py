import sys


str1 = list(sys.stdin.readline().rstrip())
str2 = list(sys.stdin.readline().rstrip())
if len(str1) * len(str2) == 0:
    print(0)
else:
    dp = [[] for _ in range(len(str1))]

    if str1[0] in str2:
        dp[0] = [(str1[0], str2.index(str1[0]) + 1), ('', 0)]
    else:
        dp[0] = [('', 0)]

    for idx in range(1, len(str1)):
        for item in dp[idx-1]:
            for i in range(item[1], len(str2)):
                if str2[i] == str1[idx]:
                    dp[idx].append((item[0] + str1[idx], i + 1))
            dp[idx].append(item)

        dp[idx].sort(key=lambda x: (x[1], -len(x[0])))
        useful = [dp[idx][0]]
        late_idx = dp[idx][0][1]
        for item_ in dp[idx]:
            if item_[1] > late_idx:
                useful.append(item_)
                late_idx = item_[1]
        dp[idx] = useful
    dp[len(str1)-1].sort(key=lambda x: (-len(x[0]), x[1]))
    res = dp[len(str1)-1][0][0]
    print(len(res), res, sep='\n')
