#include <iostream>
#include <algorithm>
using namespace std;

const int MAX_N = 100;
int grid[MAX_N][MAX_N];
bool visit[MAX_N][MAX_N];

int dy[] {0, 1, 0, -1};
int dx[] {1, 0, -1, 0};

int N, rainAmount;


void dfs(int r, int c) {
    if (0 <= r && r < N && 0 <= c && c < N && grid[r][c] > rainAmount && !visit[r][c]) {
        visit[r][c] = true;
        
        for (int i = 0; i < 4; i++) {
            dfs(r + dy[i], c + dx[i]);
        }
    }
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            cin >> grid[r][c];
        }
    }

    int ans = 0;
    for (rainAmount = 0; rainAmount < 100; rainAmount++) {
        fill(&visit[0][0], &visit[0][0] + MAX_N * MAX_N, 0);
        
        int cnt = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] > rainAmount && !visit[r][c]) {
                    cnt++;
                    dfs(r, c);
                }
            }
        }
        ans = max(ans, cnt);
    }
    cout << ans;
    return 0;
}