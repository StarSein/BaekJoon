#include <bits/stdc++.h>
using namespace std;
#define INF 2e5

int n, K;
vector<vector<int>> graph;
vector<vector<int>> childs;
vector<bool> isLeaf;
int ans = 0;

void makeTree(int curNode, int parNode) {
    for (int &nextNode : graph[curNode]) {
        if (nextNode != parNode) {
            isLeaf[curNode] = false;
            childs[curNode].push_back(nextNode);
            makeTree(nextNode, curNode);
        }
    }
}

int dfs(int curNode) {
    int minDist = INF;
    int maxDist = 0;
    if (isLeaf[curNode]) {
        minDist = maxDist = K + 1;
    } else {
        for (int &childNode : childs[curNode]) {
            int curDist = dfs(childNode) + 1;
            minDist = min(minDist, curDist);
            maxDist = max(maxDist, curDist);
        }
    }
    int ret = (minDist + maxDist <= 2 * K + 1 ? minDist : maxDist);
    
    if (ret > 2 * K) {
        ans++;
        ret = 0;
    }
    return ret;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> K;
    graph.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v; cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    childs.resize(n + 1);
    isLeaf.resize(n + 1, true);
    makeTree(1, 0);
    int rootDist = dfs(1);
    if (rootDist > K) {
        ans++;
    }
    cout << ans;
    return 0;
}