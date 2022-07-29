#include <iostream>
using namespace std;

const int MAX_N = 50;
char grid[MAX_N][MAX_N];

int dy[4] {0, 1, 0, -1};
int dx[4] {1, 0, -1, 0};

int N;
int ans = 0;

int getRowMax(int r) {
    int curCnt = 1, ret = 0;
    for (int c = 1; c < N; c++) {
        if (grid[r][c] == grid[r][c-1]) {
            curCnt++;
        } else {
            curCnt = 1;
        }
        if (curCnt > ret) {
            ret = curCnt;
        }
    }
    return ret;
}

int getColMax(int c) {
    int curCnt = 1, ret = 1;
    for (int r = 1; r < N; r++) {
        if (grid[r][c] == grid[r-1][c]) {
            curCnt++;
            ret = max(ret, curCnt);
        } else {
            curCnt = 1;
        }
    }
    return ret;
}

void updateAns(int r1, int c1, int r2 = -1, int c2 = -1) {
    ans = max(ans, getRowMax(r1));
    ans = max(ans, getColMax(c1));
    if (r2 != -1 && r2 != r1) {
        ans = max(ans, getRowMax(r2));
    }
    if (c2 != -1 && c2 != c1) {
        ans = max(ans, getRowMax(c2));
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    cin.ignore();
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            grid[r][c] = cin.get();
        }
        cin.get();
    }

    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            updateAns(r, c);
            char &cur = grid[r][c];
            for (int i = 0; i < 4; i++) {
                int nr = r + dy[i], nc = c + dx[i];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && grid[nr][nc] != cur) {
                    char &adj = grid[nr][nc];
                    swap(cur, adj);
                    updateAns(r, c, nr, nc);
                    swap(cur, adj);
                }
            }
        }
    }
    cout << ans;
    return 0;
}