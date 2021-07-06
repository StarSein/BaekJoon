import sys


T = int(sys.stdin.readline())
res = ""
for case in range(T):
    N, K = map(int, sys.stdin.readline().split())
    l_D = list(map(int, sys.stdin.readline().split()))
    l_follow = [[] for _ in range(N)]
    l_precede = [[] for _ in range(N)]
    for _ in range(K):
        X, Y = map(int, sys.stdin.readline().split())
        l_follow[X-1].append(Y-1)
        l_precede[Y-1].append(X-1)
    W = int(sys.stdin.readline()) - 1

    buildArray = list()
    for bdg in range(N):
        if not l_precede[bdg]:
            buildArray.append(bdg)

    time = 0
    while l_precede[W]:
        minBuildTime = 100000
        for bdg in buildArray:
            minBuildTime = min(minBuildTime, l_D[bdg])
        time += minBuildTime
        tempPush = list()
        set_buildComplete = set()
        for bdg in buildArray:
            l_D[bdg] -= minBuildTime
            if l_D[bdg] == 0:
                set_buildComplete.add(bdg)
                if l_follow[bdg]:
                    for nextBuild in l_follow[bdg]:
                        l_precede[nextBuild].remove(bdg)
                        if not l_precede[nextBuild]:
                            tempPush.append(nextBuild)
        tempExist = list()
        for bdg in buildArray:
            if bdg not in set_buildComplete:
                tempExist.append(bdg)
        buildArray = tempExist + tempPush

    res = "%s\n%d" % (res, time + l_D[W])
sys.stdout.write(res.lstrip())
