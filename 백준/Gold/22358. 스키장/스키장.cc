#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;
typedef long long ll;
typedef pair<int, int> pi;

const int MAX_N = 1e5+1, MAX_K = 10+1;
ll dp[MAX_N][MAX_K];

int n, m, k, s, t;
vector<vector<pi>> digraph, revgraph;


void initDP() {
    fill(&dp[0][0], &dp[0][0] + MAX_N * MAX_K, -1);
    
    dp[s][0] = 0;
    for (int node = s; node <= n; node++) {
        if (dp[node][0] >= 0) {
            for (auto& [nextNode, time] : digraph[node]) {
                dp[nextNode][0] = max(dp[nextNode][0], dp[node][0] + time);
            }
        }
    }
}

void makeDP() {
    for (int i = 1; i <= k; i++) {
        for (int node = 1; node <= n; node++) {
            for (auto& [from, time] : revgraph[node]) {
                if (dp[from][i] != -1 && dp[from][i] + time > dp[node][i]) {
                    dp[node][i] = dp[from][i] + time;
                }
            }
            for (auto& [to, time] : digraph[node]) {
                if (dp[to][i-1] > dp[node][i]) {
                    dp[node][i] = dp[to][i-1];
                }
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m >> k >> s >> t;
    digraph.resize(n+1, vector<pi>());
    revgraph.resize(n+1, vector<pi>());

    int a, b, w;
    for (int i = 0; i < m; i++) {
        cin >> a >> b >> w;
        digraph[a].emplace_back(b, w);
        revgraph[b].emplace_back(a, w);
    }

    initDP();
    makeDP();
    cout << dp[t][k];
}