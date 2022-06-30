#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second
typedef pair<int, char> pic;

const int MAX_N = 10;

int n;
pic grid[5][5], tmp[3][5][5];
int effect[MAX_N][4][4], tef[4][4];
char element[MAX_N][4][4], tel[4][4];
int bit = 0, answer = 0;

int sr[4] = {0, 0, 1, 1};
int sc[4] = {0, 1, 0, 1};

map<char, int> score;

void rotate(int gred) {
    for (int r = 0; r < 4; r++) {
        for (int c = 0; c < 4; c++) {
            tef[3-c][r] = effect[gred][r][c];
            tel[3-c][r] = element[gred][r][c];
        }
    }
    for (int r = 0; r < 4; r++) {
        for (int c = 0; c < 4; c++) {
            effect[gred][r][c] = tef[r][c];
            element[gred][r][c] = tel[r][c];
        }
    }
}

void deploy(int gred, int _sr, int _sc) {
    int t;
    for (int r = 0; r < 4; r++) {
        for (int c = 0; c < 4; c++) {
            t = grid[r+_sr][c+_sc].X + effect[gred][r][c];
            if (t < 0) t = 0;
            else if (t > 9) t = 9;
            grid[r+_sr][c+_sc].X = t;
            if (element[gred][r][c] != 'W') {
                grid[r+_sr][c+_sc].Y = element[gred][r][c];
            }
        }
    }
}

void calc() {
    int sum = 0;
    for (int r = 0; r < 5; r++) {
        for (int c = 0; c < 5; c++) {
            sum += score[grid[r][c].Y] * grid[r][c].X;
        }
    }
    if (sum > answer) {
        answer = sum;
    }
}


void dfs(int gred, int pos) {
    for (int i = 0; i < 4; i++) {
        rotate(gred);
        for (int j = 0; j < 4; j++) {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    tmp[pos][r][c] = grid[r][c];
                }
            }
            deploy(gred, sr[j], sc[j]);
            if (pos == 2) {
                calc();
            } else {
                for (int g = 0; g < n; g++) {
                    if (~bit & 1 << g) {
                        bit |= 1 << g;
                        dfs(g, pos + 1);
                        bit ^= 1 << g;
                    }
                }
            }
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    grid[r][c] = tmp[pos][r][c];
                }
            }
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                cin >> effect[i][r][c];
            }
        }
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                cin >> element[i][r][c];
            }
        }
    }

    for (int r = 0; r < 5; r++) {
        for (int c = 0; c < 5; c++) {
            grid[r][c].Y = 'W';
        }
    }

    score.emplace('R', 7);
    score.emplace('B', 5);
    score.emplace('G', 3);
    score.emplace('Y', 2);
    score.emplace('W', 0);

    for (int i = 0; i < n; i++) {
        bit |= 1 << i;
        dfs(i, 0);
        bit ^= 1 << i;
    }
    cout << answer;
}