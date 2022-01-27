import sys


class Mystring:
    def __init__(self, s):
        self.s = s
        self.arr = []
        tmp = ''
        for idx, char in enumerate(self.s):
            if char.isdigit():
                tmp += char
            else:
                if len(tmp):
                    self.arr.append(tmp)
                    tmp = ''
                self.arr.append(char)
        if len(tmp):
            self.arr.append(tmp)

    def __lt__(self, other): # '<'
        length = min(len(self.arr), len(other.arr))
        for i in range(length):
            if self.arr[i].isdigit() and other.arr[i].isdigit():
                if int(self.arr[i]) == int(other.arr[i]):
                    if len(self.arr[i]) != len(other.arr[i]):
                        return len(self.arr[i]) < len(other.arr[i])
                    else:
                        continue
                else:
                    return int(self.arr[i]) < int(other.arr[i])

            elif self.arr[i].isdigit() or other.arr[i].isdigit():
                return self.arr[i].isdigit()

            else:
                tmpA = self.arr[i].lower()
                tmpB = other.arr[i].lower()
                if tmpA != tmpB:
                    return tmpA < tmpB
                elif self.arr[i] != other.arr[i]:
                    return self.arr[i].isupper()
                else:
                    continue
        return len(self.arr) < len(other.arr)

    def __gt__(self, other): # '>'
        length = min(len(self.arr), len(other.arr))
        for i in range(length):
            if self.arr[i].isdigit() and other.arr[i].isdigit():
                if int(self.arr[i]) == int(other.arr[i]):
                    if len(self.arr[i]) != len(other.arr[i]):
                        return len(self.arr[i]) > len(other.arr[i])
                    else:
                        continue
                else:
                    return int(self.arr[i]) > int(other.arr[i])

            elif self.arr[i].isdigit() or other.arr[i].isdigit():
                return other.arr[i].isdigit()

            else:
                tmpA = self.arr[i].lower()
                tmpB = other.arr[i].lower()
                if tmpA != tmpB:
                    return tmpA > tmpB
                elif self.arr[i] != other.arr[i]:
                    return self.arr[i].islower()
                else:
                    continue
        return len(self.arr) > len(other.arr)

    def __eq__(self, other): # '=='
        length = min(len(self.arr), len(other.arr))
        for i in range(length):
            if self.arr[i].isdigit() and other.arr[i].isdigit():
                if int(self.arr[i]) == int(other.arr[i]):
                    if len(self.arr[i]) != len(other.arr[i]):
                        return False
                    else:
                        continue
                else:
                    return False

            elif self.arr[i].isdigit() or other.arr[i].isdigit():
                return False

            else:
                if self.arr[i] != other.arr[i]:
                    return False
                else:
                    continue
        return len(self.arr) == len(other.arr)

    def __str__(self):
        return self.s


def solution():
    obj_list = [Mystring(s) for idx, s in enumerate(inp_list)]
    obj_list.sort()
    for idx, obj in enumerate(obj_list):
        print(obj)


input = sys.stdin.readline


if __name__ == '__main__':
    n = int(input())
    inp_list = [input().rstrip() for inp in range(n)]
    solution()
