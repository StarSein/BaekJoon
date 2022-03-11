import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    NOT_CHECKED = -1

    def dfs(start_node: int):
        visited_stack = []
        visited_set = set()

        flag = False
        curr_node = start_node
        while curr_node not in visited_set:
            visited_stack.append(curr_node)
            visited_set.add(curr_node)
            curr_node = choice_list[curr_node-1]
            if is_contained[curr_node] != NOT_CHECKED:
                flag = True
                break
        if flag:
            for node in visited_stack:
                is_contained[node] = False
        else:
            cycle_end = curr_node

            while True:
                curr_node = visited_stack.pop()
                is_contained[curr_node] = True
                if curr_node == cycle_end:
                    break

            while len(visited_stack):
                curr_node = visited_stack.pop()
                is_contained[curr_node] = False

    t = int(input())
    for tc in range(t):
        n = int(input())
        choice_list = list(map(int, input().split()))

        is_contained = [NOT_CHECKED] * (n + 1)
        for stud in range(1, n + 1):
            if is_contained[stud] == NOT_CHECKED:
                dfs(stud)

        answer = n - (sum(is_contained) + 1)
        print(answer)


if __name__ == '__main__':
    main()
