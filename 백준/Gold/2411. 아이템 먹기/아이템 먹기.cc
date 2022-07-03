#include <iostream>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 101;
int grid[MAX_N][MAX_N];
pi dp[MAX_N][MAX_N];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, a, b; cin >> n >> m >> a >> b;
    int r, c;
    for (int i = 0; i < a; i++) {
        cin >> r >> c;
        grid[r][c] = 1;
    }
    for (int i = 0; i < b; i++) {
        cin >> r >> c;
        grid[r][c] = -1;
    }
    dp[1][0] = {1, 0};
    for (int r = 1; r <= n; r++) {
        for (int c = 1; c <= m; c++) {
            if (grid[r][c] == -1) {
                dp[r][c] = {0, 0};
            } else {
                if (dp[r-1][c].second == dp[r][c-1].second) {
                    dp[r][c] = {dp[r-1][c].first + dp[r][c-1].first, dp[r-1][c].second};
                } else {
                    dp[r][c] = max(dp[r-1][c], dp[r][c-1], [](pi a, pi b)->bool{return a.second < b.second;});
                }
                if (grid[r][c] == 1) {
                    dp[r][c].second++;
                }
            }
        }
    }
    cout << dp[n][m].first;
}