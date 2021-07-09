import sys


def binarySearch(minHeight, maxHeight, require):
    amount = 0
    if maxHeight - minHeight <= 1:
        for idx in range(len(l_height)):
            amount += l_height[idx] - maxHeight
        if amount >= require:
            return maxHeight
        else:
            return minHeight
    else:
        midHeight = (minHeight + maxHeight) // 2
        for idx in range(len(l_height)):
            if l_height[idx] > midHeight:
                amount += l_height[idx] - midHeight
        if amount > require:
            return binarySearch(midHeight, maxHeight, require)
        elif amount < require:
            return binarySearch(minHeight, midHeight, require)
        else:
            return midHeight


N, M = map(int, sys.stdin.readline().split())
l_height = list(map(int, sys.stdin.readline().split()))
stack = []
print(binarySearch(0, 2000000000, M))
