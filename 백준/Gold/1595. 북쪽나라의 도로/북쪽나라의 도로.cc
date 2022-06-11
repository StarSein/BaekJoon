#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;


const int MAX_N = 10'000, ROOT = 1;
vector<vector<pi>> graph(MAX_N + 1, vector<pi>());
vector<int> dp1(MAX_N + 1), dp2(MAX_N + 1);


void makeDP(int curNode, int parentNode) {
    int maxDia = 0;
    int curLen, maxLen = 0, secLen = 0;
    for (pi& e : graph[curNode]) {
        if (e.first != parentNode) {
            makeDP(e.first, curNode);
            maxDia = max(maxDia, dp1[e.first]);
            curLen = dp2[e.first] + e.second;
            if (curLen > maxLen) {
                secLen = maxLen;
                maxLen = curLen;
            } else if (curLen > secLen) {
                secLen = curLen;
            }
        }
    }
    dp1[curNode] = max(maxDia, maxLen + secLen);
    dp2[curNode] = maxLen;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int u, v, cost;
    while (cin >> u >> v >> cost) {
        graph[u].emplace_back(v, cost);
        graph[v].emplace_back(u, cost);
    }

    makeDP(ROOT, 0);
    cout << dp1[ROOT];
}