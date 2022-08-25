#include <bits/stdc++.h>
using namespace std;
typedef tuple<int, int, int> ti;

typedef struct cmp {
    bool operator()(ti &a, ti &b) {
        return get<0>(a) > get<0>(b);
    }
} cmp;

priority_queue<ti, vector<ti>, cmp> pq;

const int SZ = 1000;
int grid[SZ][SZ];
bool visited[SZ][SZ];

int dy[] {0, 1, 0, -1};
int dx[] {1, 0, -1, 0};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int m, n; cin >> m >> n;
    for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
            cin >> grid[r][c];
        }
    }

    if (grid[0][0] == -1) {
        cout << -1;
        return 0;
    }
    pq.emplace(grid[0][0], 0, 0);
    while (!pq.empty()) {
        auto [cost, row, col] = pq.top();
        pq.pop();

        if (row == m - 1 && col == n - 1) {
            cout << cost;
            return 0;
        }

        if (visited[row][col]) {
            continue;
        }

        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int nr = row + dy[i];
            int nc = col + dx[i];
            if (0 <= nr && nr < m && 0 <= nc && nc < n && grid[nr][nc] != -1 && !visited[nr][nc]) {
                pq.emplace(cost + grid[nr][nc], nr, nc);
            }
        }
    }
    cout << -1;
    return 0;
}