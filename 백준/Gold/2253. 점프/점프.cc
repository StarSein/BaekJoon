#include <iostream>
#include <algorithm>
using namespace std;


const int MAX_N = 20'000, MAX_X = 150, INF = 30'000;
int dp[MAX_N][MAX_X];

bool disable[MAX_N];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    int d;
    for (int i = 0; i < m; i++) {
        cin >> d;
        disable[d] = true;
    }
    fill(&dp[0][0], &dp[0][0] + MAX_N * MAX_X, INF);
    if (!disable[2]) {
        dp[2][1] = 1;
    }
    for (int i = 2; i < n; i++) {
        for (int j = 1; j < MAX_X-1; j++) {
            if (dp[i][j] != INF) {
                if (!disable[i+j-1] && j != 1) dp[i+j-1][j-1] = min(dp[i+j-1][j-1], dp[i][j] + 1);
                if (!disable[i+j]) dp[i+j][j] = min(dp[i+j][j], dp[i][j] + 1);
                if (!disable[i+j+1]) dp[i+j+1][j+1] = min(dp[i+j+1][j+1], dp[i][j] + 1);
            }
        }
    }
    int ans = INF;
    for (int j = 1; j < MAX_X; j++) {
        ans = min(ans, dp[n][j]);
    }
    cout << (ans != INF ? ans : -1);
}