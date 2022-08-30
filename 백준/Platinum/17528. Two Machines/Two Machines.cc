#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 250, MAX_VAL = 250 * 250, INF = 1e9;
int dp[MAX_N][MAX_VAL + 1];

vector<pi> vWork;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    fill(&dp[0][0], &dp[0][0] + MAX_N * (MAX_VAL + 1), INF);

    int n; cin >> n;
    vWork.reserve(n);
    for (int i = 0; i < n; i++) {
        int a, b; cin >> a >> b;
        vWork.emplace_back(a, b);
    }

    dp[0][0] = vWork[0].second;
    dp[0][vWork[0].first] = 0;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j <= MAX_VAL; j++) {
            if (dp[i][j] != INF) {
                int &choice1 = dp[i + 1][j];
                int &choice2 = dp[i + 1][j + vWork[i + 1].first];
                choice1 = min(choice1, dp[i][j] + vWork[i + 1].second);
                choice2 = min(choice2, dp[i][j]);
            }
        }
    }
    int ans = INF;
    for (int i = 0; i <= MAX_VAL; i++) {
        ans = min(ans, max(i, dp[n - 1][i]));
    }
    cout << ans;
    return 0;
}