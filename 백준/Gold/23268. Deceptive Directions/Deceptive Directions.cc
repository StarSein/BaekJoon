#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

const int MAX_H = 1000, MAX_W = 1000;
string grid[MAX_H];
string seq;
int minDist[MAX_H][MAX_W];
bool visited[MAX_H][MAX_W];

int dy[] {0, 1, 0, -1};
int dx[] {1, 0, -1, 0};

queue<pi> q;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int w, h; cin >> w >> h;
    for (int r = 0; r < h; r++) {
        cin >> grid[r];
    }
    cin >> seq;

    int sr = -1, sc = -1;
    for (int r = 0; r < h; r++) {
        for (int c = 0; c < w; c++) {
            if (grid[r][c] == 'S') {
                sr = r;
                sc = c;
                break;
            }
        }
        if (sr != -1) {
            break;
        }
    }

    memset(minDist, -1, sizeof(minDist));

    q.emplace(sr, sc);
    minDist[sr][sc] = 0;
    while (!q.empty()) {
        int sz = q.size();
        for (int i = 0; i < sz; i++) {
            auto &[r, c] = q.front();
            for (int j = 0; j < 4; j++) {
                int nr = r + dy[j];
                int nc = c + dx[j];
                if (0 <= nr && nr < h && 0 <= nc && nc < w \
                && grid[nr][nc] != '#' && minDist[nr][nc] == -1) {
                    q.emplace(nr, nc);
                    minDist[nr][nc] = minDist[r][c] + 1;
                }
            }
            q.pop();
        }
    }

    q.emplace(sr, sc);
    for (char &dir : seq) {
        int sz = q.size();
        for (int i = 0; i < sz; i++) {
            auto &[r, c] = q.front();
            for (int j = 0; j < 4; j++) {
                if (j == 0 && dir == 'E') continue;
                if (j == 1 && dir == 'S') continue;
                if (j == 2 && dir == 'W') continue;
                if (j == 3 && dir == 'N') continue;

                int nr = r + dy[j];
                int nc = c + dx[j];
                if (0 <= nr && nr < h && 0 <= nc && nc < w \
                && grid[nr][nc] != '#' && !visited[nr][nc]) {
                    q.emplace(nr, nc);
                    visited[nr][nc] = true;
                }
            }
            q.pop();
        }
    }
    while (!q.empty()) {
        auto &[r, c] = q.front();
        if (minDist[r][c] == seq.size())
            grid[r][c] = '!';
        q.pop();
    }
    copy(grid, grid + h, ostream_iterator<string>(cout, "\n"));
    return 0;
}