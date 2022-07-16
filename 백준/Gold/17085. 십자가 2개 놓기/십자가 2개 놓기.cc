#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;
typedef pair<int, int> pi;

const int SZ = 15, INF = 30;
string grid[SZ];
int area[SZ][SZ], check[SZ][SZ];

int n, m;
int ans = 0;
vector<pi> vec;

int dy[4] {0, 1, 0, -1};
int dx[4] {1, 0, -1, 0};


void drawCheck(int r, int c, int a) {
    int d = a / 4;
    for (int i = 0; i < 4; i++) {
        for (int j = d; j > 0; j--) {
            check[r+dy[i]*j][c+dx[i]*j] = 1;
        }
    }
    check[r][c] = 1;
}

bool isAble(int r, int c, int a) {
    int d = a / 4;
    for (int i = 0; i < 4; i++) {
        for (int j = d; j > 0; j--) {
            if (check[r+dy[i]*j][c+dx[i]*j]) {
                return false;
            }
        }
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> grid[i];
    }

    int d, nr, nc;
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            if (grid[r][c] == '#') {
                int &minA = area[r][c];
                minA = INF;
                for (int i = 0; i < 4; i++) {
                    d = 0;
                    do {
                        d++;
                        nr = r + dy[i] * d;
                        nc = c + dx[i] * d;
                    } while (0 <= nr && nr < n && 0 <= nc && nc < m && grid[nr][nc] == '#');
                    minA = min(minA, d - 1);
                }
                minA = 4 * minA + 1;
            }
        }
    }

    int aLimit = 2 * min(n, m);
    for (int a1 = 1; a1 < aLimit; a1 += 4) {
        for (int a2 = 1; a2 < aLimit; a2 += 4) {
            vec.emplace_back(a1, a2);
        }
    }
    sort(vec.begin(), vec.end(), [](pi &a, pi &b) {return a.first * a.second > b.first * b.second;});    
    
    for (auto &[a1, a2] : vec) {
        for (int r1 = 0; r1 < n; r1++) {
            for (int c1 = 0; c1 < m; c1++) {
                if (area[r1][c1] >= a1) {
                    memset(check, 0, sizeof(check));
                    drawCheck(r1, c1, a1);
                    for (int r2 = 0; r2 < n; r2++) {
                        for (int c2 = 0; c2 < m; c2++) {
                            if (area[r2][c2] >= a2 && !check[r2][c2]) {
                                if (isAble(r2, c2, a2)) {
                                    cout << a1 * a2;
                                    return 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}