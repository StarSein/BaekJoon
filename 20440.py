import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    time_list = []
    for i in range(n):
        enter_time, exit_time = map(int, input().split())
        time_list.append((enter_time, exit_time))
    time_list.sort()

    heap = []
    best_num = 0
    best_enter = 0
    best_exit = 0
    last_exit = 0
    for i in range(n):
        enter_time, exit_time = time_list[i]
        heapq.heappush(heap, exit_time)
        while len(heap) and (heap[0] <= enter_time):
            last_exit = heapq.heappop(heap)
        cur_num = len(heap)
        if cur_num > best_num:
            best_num = cur_num
            best_enter = enter_time
            best_exit = heap[0]
        elif cur_num == best_num and enter_time == last_exit:
            best_exit = heap[0]

    print(best_num)
    print(best_enter, best_exit)


if __name__ == '__main__':
    main()
