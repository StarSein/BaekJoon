#include <iostream>
#include <cstring>
using namespace std;
typedef long long ll;

const int MAX_N = 10, MAX_M = 2000;
ll dp[MAX_N+2][2*MAX_M+1];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t; cin >> t;
    while (t--) {
        int n, m; cin >> n >> m;
        memset(dp, 0, sizeof(dp));

        fill(&dp[n+1][0], &dp[n+1][0] + 2 * m + 1, 1);

        for (int r = n; r >= 1; r--) {
            for (int c = m; c >= 1; c--) {
                dp[r][c] = dp[r+1][2*c] + dp[r][c+1];
            }
        }
        cout << dp[1][1] << '\n';
    }
}