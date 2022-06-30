#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 50;
int n, m;
int grid[MAX_N][MAX_N], room[MAX_N][MAX_N]; 
bool visit[MAX_N][MAX_N];
int roomCnt = 0;
int area[MAX_N * MAX_N + 1];
int ans3 = 0;

int dy[4] {0, -1, 0, 1};
int dx[4] {-1, 0, 1, 0};

void makeRoom(int _r, int _c) {
    int _area = 0;
    queue<pi> q;
    q.emplace(_r, _c);
    visit[_r][_c] = true;
    int r, c, nr, nc;
    while (!q.empty()) {
        tie(r, c) = q.front();
        q.pop();
        room[r][c] = roomCnt;
        _area++;
        
        for (int i = 0; i < 4; i++) {
            if (~grid[r][c] & 1 << i) {
                nr = r + dy[i]; nc = c + dx[i];
                if (0 <= nr && nr < m && 0 <= nc && nc < n && !visit[nr][nc]) {
                    q.emplace(nr, nc);
                    visit[nr][nc] = true;
                }
            }
        }
    }
    area[roomCnt] = _area;
}

void calcAns3(int _r, int _c) {
    int curRoom = room[_r][_c];
    int curArea = area[curRoom];
    queue<pi> q;
    q.emplace(_r, _c);
    visit[_r][_c] = true;
    int r, c, nr, nc;
    while (!q.empty()) {
        tie(r, c) = q.front();
        q.pop();

        for (int i = 0; i < 4; i++) {
            nr = r + dy[i]; nc = c + dx[i];
            if (0 <= nr && nr < m && 0 <= nc && nc < n) {
                if (room[nr][nc] == curRoom) {
                    if (!visit[nr][nc]) {
                        q.emplace(nr, nc);
                        visit[nr][nc] = true;
                    }
                } else {
                    ans3 = max(ans3, curArea + area[room[nr][nc]]);
                }
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m;
    for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
            cin >> grid[r][c];
        }
    }

    for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
            if (!visit[r][c]) {
                roomCnt++;
                makeRoom(r, c);
            }
            visit[r][c] = false;
        }
    }

    for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
            if (!visit[r][c]) {
                calcAns3(r, c);
            }
        }
    }

    cout << roomCnt << '\n' \
    << *max_element(area + 1, area + roomCnt + 1) << '\n' \
    << ans3;
}