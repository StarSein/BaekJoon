#include <iostream>
#include <queue>
#include <tuple>
using namespace std;
typedef tuple<int, int, int> ti;

const int MAX_N = 50;
bool visit[MAX_N][MAX_N];
string grid[MAX_N];

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

    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> grid[i];
    }

    pq.emplace(0, 0, 0);
    int dist, r, c, nr, nc;
    while (!pq.empty()) {
        tie(dist, r, c) = pq.top();
        pq.pop();
        if (r == n-1 && c == n-1) {
            cout << dist;
            return 0;
        }
        if (visit[r][c]) {
            continue;
        }
        visit[r][c] = true;
        for (int i = 0; i < 4; i++) {
            nr = r + dy[i]; nc = c + dx[i];
            if (0 <= nr && nr < n && 0 <= nc && nc < n) {
                if (!visit[nr][nc]) {
                    pq.emplace(dist + 1 - (grid[nr][nc]-'0'), nr, nc);
                }
            }
        }
    }

}