#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

const int MAX_N = 1e5, MAX_LOG = 21, INF = 1e8; 
int logMax;
vector<vector<int>> graph;
int dp[MAX_N + 1][MAX_LOG + 1];

void dfs(int curNode, int parNode) {
    for (int &nextNode : graph[curNode]) {
        if (nextNode != parNode) {
            dfs(nextNode, curNode);
            for (int curColor = 1; curColor <= logMax; curColor++) {
                int minCost = INF;
                for (int childColor = 1; childColor <= logMax; childColor++) {
                    if (childColor != curColor && dp[nextNode][childColor] < minCost) {
                        minCost = dp[nextNode][childColor];
                    }
                }
                dp[curNode][curColor] += minCost;
            }
        }
    }
    for (int curColor = 1; curColor <= logMax; curColor++) {
        dp[curNode][curColor] += curColor;
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int n; cin >> n;
    graph.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v; cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    logMax = static_cast<int>(log2(n)) + 1;

    dfs(1, 0);
    int ans = INF;
    for (int color = 1; color <= logMax; color++) {
        if (dp[1][color] < ans) {
            ans = dp[1][color];
        }
    }
    cout << ans;
    return 0;
}