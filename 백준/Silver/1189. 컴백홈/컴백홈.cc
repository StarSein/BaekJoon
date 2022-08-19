#include <iostream>
#include <vector>
using namespace std;

const int MAX_SZ = 5;
int R, C, K;
vector<string> grid;
int ans = 0;
bool visit[MAX_SZ][MAX_SZ];

int dy[] {0, 1, 0, -1};
int dx[] {1, 0, -1, 0};

void dfs(int r, int c, int dist) {
    if (dist == K) {
        if (r == 0 && c == C - 1) {
            ans++;
        }
        return;
    }
    for (int i = 0; i < 4; i++) {
        int nr = r + dy[i], nc = c + dx[i];
        if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] != 'T' && !visit[nr][nc]) {
            visit[nr][nc] = true;
            dfs(nr, nc, dist + 1);
            visit[nr][nc] = false;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    cin >> R >> C >> K;
    grid.resize(R);
    for (int i = 0; i < R; i++) {
        cin >> grid[i];
    }
    
    visit[R-1][0] = true;
    dfs(R - 1, 0, 1);
    cout << ans;
    return 0;
}