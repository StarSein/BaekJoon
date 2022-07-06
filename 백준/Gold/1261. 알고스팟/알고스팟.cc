#include <iostream>
#include <queue>
#include <tuple>
using namespace std;
typedef tuple<int, int, int> ti;

const int MAX_N = 100;

int grid[MAX_N][MAX_N];
bool visit[MAX_N][MAX_N];

struct cmp {
    bool operator() (ti& a, ti& b) {
        return get<0>(a) > get<0>(b);
    }
};

priority_queue<ti, vector<ti>, cmp> pq;

int dy[4] {0, 1, 0, -1};
int dx[4] {1, 0, -1, 0};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int m, n; cin >> m >> n;
    cin.ignore();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            grid[i][j] = cin.get() - '0';
        }
        cin.get();
    }

    pq.emplace(0, 0, 0);
    int d, r, c, nd, nr, nc;
    while (!pq.empty()) {
        tie(d, r, c) = pq.top();
        pq.pop();
        if (visit[r][c]) {
            continue;
        }
        if (r == n-1 && c == m-1) {
            cout << d;
            return 0;
        }
        visit[r][c] = true;
        for (int i = 0; i < 4; i++) {
            nr = r + dy[i], nc = c + dx[i];
            if (0 <= nr && nr < n && 0 <= nc && nc < m && !visit[nr][nc]) {
                nd = (grid[nr][nc] ? d + 1 : d);
                pq.emplace(nd, nr, nc);
            }
        }
    }
}