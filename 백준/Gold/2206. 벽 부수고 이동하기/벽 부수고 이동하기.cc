#include <iostream>
#include <queue>
#include <tuple>
#include <vector>
using namespace std;
typedef tuple<int, int, int, int> ti;

vector<string> grid;

queue<ti> q;
vector<vector<vector<bool>>> visit;

int dy[4] {0, 1, 0, -1};
int dx[4] {1, 0, -1, 0};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    grid.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> grid[i];
    }

    visit.resize(n, vector<vector<bool>>(m, vector<bool>(2, false)));
    q.emplace(1, 0, 0, 0);
    int dist, r, c, cnt, nr, nc, ncnt;
    while (!q.empty()) {
        tie(dist, r, c, cnt) = q.front();
        q.pop();

        if (r == n-1 && c == m-1) {
            cout << dist;
            return 0;
        }

        for (int i = 0; i < 4; i++) {
            nr = r + dy[i], nc = c + dx[i];
            if (0 <= nr && nr < n && 0 <= nc && nc < m) {
                ncnt = (grid[nr][nc] == '1' ? cnt + 1 : cnt);
                if (ncnt != 2 && !visit[nr][nc][ncnt]) {
                    q.emplace(dist + 1, nr, nc, ncnt);
                    visit[nr][nc][ncnt] = true;
                }
            }
        }
    }
    cout << -1;
    return 0;
}