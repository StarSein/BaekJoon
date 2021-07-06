import sys


T = int(sys.stdin.readline())
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

    for bdg in range(N):
        if not l_precede[bdg]:
            startBuild = bdg
            break
    buildArray = list()
    buildArray.append(startBuild)
    time = 0
    while l_precede[W]:
        minBuildTime = 100000
        for bdg in buildArray:
            minBuildTime = min(minBuildTime, l_D[bdg])
        time += minBuildTime
        temp = list()
        l_buildComplete = list()
        for bdg in buildArray:
            l_D[bdg] -= minBuildTime
            if l_D[bdg] == 0:
                l_buildComplete.append(bdg)
                if l_follow[bdg]:
                    for nextBuild in l_follow[bdg]:
                        l_precede[nextBuild].remove(l_buildComplete[-1])
                        if not l_precede[nextBuild]:
                            temp.append(nextBuild)
        for bC in l_buildComplete:
            buildArray.remove(bC)
        buildArray.extend(temp)

    print(time + l_D[W])
