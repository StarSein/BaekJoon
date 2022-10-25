#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>
using namespace std;
typedef pair<int, int> pi;

const int MOD = 10;
int N, M;
vector<string> grid;
vector<vector<int>> dp, id;
vector<vector<bool>> visited;
int compoCnt = 0;
int dy[] {0, 1, 0, -1};
int dx[] {1, 0, -1, 0};

void bfs(int r, int c) {
    compoCnt++;
    int cnt = 0;
    queue<pi> q1, q2;
    q1.emplace(r, c);
    visited[r][c] = true;
    cnt++;
    while (!q1.empty()) {
        const auto &[row, col] = q1.front();
        q2.emplace(row, col);
        
        for (int i = 0; i < 4; i++) {
            int nr = row + dy[i];
            int nc = col + dx[i];
            if (0 <= nr && nr < N && 0 <= nc && nc < M && grid[nr][nc] == '0' && !visited[nr][nc]) {
                q1.emplace(nr, nc);
                visited[nr][nc] = true;
                cnt++;
            }
        }
        q1.pop();
    }
    while (!q2.empty()) {
        const auto &[row, col] = q2.front();
        dp[row][col] = cnt;
        id[row][col] = compoCnt;
        q2.pop();
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;
    grid.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> grid[i];
    }
    dp.resize(N, vector<int>(M));
    id.resize(N, vector<int>(M));
    visited.resize(N, vector<bool>(M));
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < M; c++) {
            if (grid[r][c] == '0' && !visited[r][c]) {
                bfs(r, c);
            }
        }
    }

    for (int r = 0; r < N; r++) {
        for (int c = 0; c < M; c++) {
            int moveCnt = 0;
            if (grid[r][c] == '1') {
                unordered_set<int> s;
                moveCnt = 1;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dy[i];
                    int nc = c + dx[i];
                    if (0 <= nr && nr < N && 0 <= nc && nc < M && grid[nr][nc] == '0' && !s.count(id[nr][nc])) {
                        moveCnt += dp[nr][nc];
                        s.insert(id[nr][nc]);
                    }
                }
            }
            cout << moveCnt % MOD;
        }
        cout << '\n';
    }
    return 0;
}