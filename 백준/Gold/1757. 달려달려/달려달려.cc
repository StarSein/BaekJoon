#include <iostream>
using namespace std;

const int MAX_N = 1e4, MAX_M = 500;

int D[MAX_N];
int dp[MAX_N][MAX_M+1];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> D[i];
    }
    dp[0][1] = D[0];

    for (int i = 1; i < n; i++) {
        for (int j = 1; j <= m; j++) {
            dp[i][j] = max(dp[i][j], dp[i-1][j-1] + D[i]);
        }
        for (int j = 1; j <= m && i >= j; j++) {
            dp[i][0] = max(dp[i][0], dp[i-j][j]);
        }
        dp[i][0] = max(dp[i][0], dp[i-1][0]);
    }
    cout << dp[n-1][0];
}