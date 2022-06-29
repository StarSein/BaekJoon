#include <iostream>
#include <queue>
#include <tuple>
#include <vector>
using namespace std;
typedef tuple<int, int, int, int> ti;

const int MAX_N = 200, MAX_K = 30, INF = 1e5;
bool grid[MAX_N][MAX_N], visit[MAX_N][MAX_N][MAX_K+1];

int k, w, h;

queue<ti> q;

int jy[8] {-1, -2, -2, -1, 1, 2, 2, 1};
int jx[8] {-2, -1, 1, 2, 2, 1, -1, -2};
int dy[4] {0, 1, 0, -1};
int dx[4] {1, 0, -1, 0};

inline bool isReachable(int r, int c, int j) {
    return 0 <= r && r < h && 0 <= c && c < w && !grid[r][c] && !visit[r][c][j];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> k;
    cin >> w >> h;
    
    for (int r = 0; r < h; r++) {
        for (int c = 0; c < w; c++) {
            cin >> grid[r][c];
        }
    }

    q.emplace(0, 0, 0, k);
    visit[0][0][k] = true;
    int d, r, c, jump, nr, nc;
    while (!q.empty()) {
        tie(d, r, c, jump) = q.front();
        q.pop();

        if (r == h-1 && c == w-1) {
            cout << d;
            return 0;
        }

        if (jump) {
            for (int i = 0; i < 8; i++) {
                nr = r + jy[i]; nc = c + jx[i];
                if (isReachable(nr, nc, jump-1)) {
                    q.emplace(d+1, nr, nc, jump-1);
                    visit[nr][nc][jump-1] = true;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            nr = r + dy[i]; nc = c + dx[i];
            if (isReachable(nr, nc, jump)) {
                q.emplace(d+1, nr, nc, jump);
                visit[nr][nc][jump] = true;
            }
        }
    }
    cout << -1;
    return 0;
}