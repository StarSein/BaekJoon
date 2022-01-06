import sys

input = sys.stdin.readline

WIDTH = 6
HEIGHT = 12

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


class Puyo:
    def __init__(self, color, loc, isBoom):
        self.color = color
        self.loc = loc
        self.isBoom = isBoom

    def determineBoom(self, objMatrix):
        visiting = [self.loc]
        visited = set()
        while visiting:
            currY, currX = visiting.pop()
            for i in range(4):
                nextY = currY + dy[i]
                nextX = currX + dx[i]
                if (nextY, nextX) in visited:
                    continue
                if not (0 <= nextY < WIDTH and 0 <= nextX < HEIGHT):
                    continue
                if objMatrix[nextY][nextX].color == self.color:
                    visiting.append((nextY, nextX))
            visited.add((currY, currX))
        if len(visited) >= 4:
            for y, x in visited:
                samePuyo = objMatrix[y][x]
                samePuyo.isBoom = True


def solution(inpMatrix):
    colorMatrix = [[inpMatrix[HEIGHT-1-x][y] for x in range(HEIGHT)] for y in range(WIDTH)]
    objMatrix = [[Puyo(colorMatrix[y][x], (y, x), False) for x in range(HEIGHT)] for y in range(WIDTH)]
    cntCombo = 0
    while True:
        isEndOfGame = True

        for col in range(WIDTH):
            for row in range(HEIGHT):
                puyo = objMatrix[col][row]
                if puyo.color != '.' and not puyo.isBoom:
                    puyo.determineBoom(objMatrix)

        for col in range(WIDTH):
            for row in range(HEIGHT-1, -1, -1):
                puyo = objMatrix[col][row]
                if puyo.isBoom:
                    isEndOfGame = False
                    objMatrix[col].pop(row)
                    objMatrix[col].append(Puyo('.', (col, -1), False))

        for col in range(WIDTH):
            for row in range(HEIGHT):
                puyo = objMatrix[col][row]
                puyo.loc = (col, row)

        if isEndOfGame:
            break
        else:
            cntCombo += 1

    return cntCombo


if __name__ == '__main__':
    inpMatrix = [input().rstrip() for _ in range(HEIGHT)]
    sol = solution(inpMatrix)
    print(sol)