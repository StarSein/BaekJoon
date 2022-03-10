import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, k = map(int, input().split())
    height_list = list(map(int, input().split()))
    total_cost = height_list[n - 1] - height_list[0]

    dist_list = [0]
    for i in range(1, n):
        dist_list.append(height_list[i] - height_list[i-1])
    dist_list.sort(reverse=True)
    total_cost -= sum(dist_list[:k-1])

    print(total_cost)


if __name__ == '__main__':
    main()
