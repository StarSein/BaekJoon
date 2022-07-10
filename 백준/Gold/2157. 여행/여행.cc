#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 301;

int dp[MAX_N][MAX_N];
vector<vector<pi>> graph;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int n, m, k; cin >> n >> m >> k;
    graph.resize(n+1, vector<pi>());
    for (int i = 0; i < k; i++) {
        int a, b, c; cin >> a >> b >> c;
        graph[b].emplace_back(a, c);
    }

    fill(&dp[0][0], &dp[0][0] + MAX_N * MAX_N, -1);
    dp[1][1] = 0;
    for (int i = 2; i <= n; i++) {
        for (int j = 2; j <= i; j++) {
            for (auto &[prevNode, cost] : graph[i]) {
                if (dp[prevNode][j-1] != -1) {
                    dp[i][j] = max(dp[i][j], dp[prevNode][j-1] + cost);
                }
            }
        }
    }
    int ans = 0;
    for (int i = 2; i <= m; i++) {
        ans = max(ans, dp[n][i]);
    }
    cout << ans;
}