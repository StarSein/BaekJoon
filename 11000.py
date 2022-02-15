import sys


input = sys.stdin.readline


def solution():
    lecture_list.sort()
    max_room = 0
    current_room = 0
    for i in range(len(lecture_list)):
        time, is_start_time = lecture_list[i]
        if is_start_time:
            current_room += 1
        else:
            current_room -= 1
        max_room = max(max_room, current_room)

    print(max_room)
        

if __name__ == '__main__':
    n = int(input())
    lecture_list = []
    for lecture in range(n):
        s, t = map(int, input().split())
        lecture_list.append((s, True))
        lecture_list.append((t, False))
    solution()
