#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX_N = 1e4 + 1;
const int INF = 2 * MAX_N;
int N, W;
int upper[MAX_N];
int lower[MAX_N];
int dp[MAX_N][3];


int solve(int l, int r, int idx) {
    for (int i = l; i <= r; i++) {
        dp[i][0] = min(dp[i][0], dp[i - 1][0] + 2);
        dp[i][1] = min(dp[i][1], dp[i - 1][0] + 1);
        dp[i][2] = min(dp[i][2], dp[i - 1][0] + 1);

        if (upper[i] + lower[i] <= W) {
            dp[i][0] = min(dp[i][0], dp[i - 1][0] + 1);
        }

        if (lower[i - 1] + lower[i] <= W && upper[i - 1] + upper[i] <= W) {
            dp[i][0] = min(dp[i][0], dp[i - 2][0] + 2);
        }

        if (lower[i - 1] + lower[i] <= W) {
            dp[i][1] = min(dp[i][1], dp[i - 1][2] + 1);
        }

        if (upper[i - 1] + upper[i] <= W) {
            dp[i][2] = min(dp[i][2], dp[i - 1][1] + 1);
        }

        dp[i][0] = min(dp[i][0], dp[i][1] + 1);
        dp[i][0] = min(dp[i][0], dp[i][2] + 1);
    }

    return dp[r][idx];
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T; cin >> T;
    while (T--) {
        cin >> N >> W;
        for (int i = 1; i <= N; i++) {
            cin >> lower[i];
        }
        for (int i = 1; i <= N; i++) {
            cin >> upper[i];
        }
        int ans = INF;
        fill(&dp[0][0], &dp[0][0] + 3 * MAX_N, INF);
        dp[0][0] = dp[0][1] = dp[0][2] = 0;
        ans = min(ans, solve(1, N, 0));

        fill(&dp[0][0], &dp[0][0] + 3 * MAX_N, INF);
        if (lower[1] + lower[N] <= W && upper[1] + upper[N] <= W) {
            dp[1][0] = 2;
            dp[1][1] = dp[1][2] = INF;
            ans = min(ans, solve(2, N - 1, 0));
        }
        fill(&dp[0][0], &dp[0][0] + 3 * MAX_N, INF);
        if (lower[1] + lower[N] <= W) {
            dp[1][0] = 2;
            dp[1][1] = 1;
            dp[1][2] = INF;
            ans = min(ans, solve(2, N, 2));
        }
        fill(&dp[0][0], &dp[0][0] + 3 * MAX_N, INF);
        if (upper[1] + upper[N] <= W) {
            dp[1][0] = 2;
            dp[1][1] = INF;
            dp[1][2] = 1;
            ans = min(ans, solve(2, N, 1));
        }
        cout << ans << '\n';
    }
    return 0;
}