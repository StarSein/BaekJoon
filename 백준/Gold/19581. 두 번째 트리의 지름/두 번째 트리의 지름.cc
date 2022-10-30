#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
using namespace std;
typedef pair<int, int> pi;

int N;
vector<vector<pi>> graph;

pi bfs(int startNode, int banned) {
    queue<pi> q;
    vector<bool> visited(N + 1);
    visited[banned] = true;

    int maxDist = 0;
    int bestNode = startNode;
    q.emplace(startNode, 0);
    visited[startNode] = true;
    while (!q.empty()) {
        auto [node, dist] = q.front();
        q.pop();

        if (dist > maxDist) {
            bestNode = node;
            maxDist = dist;
        }

        for (auto &[nextNode, weight]: graph[node]) {
            if (!visited[nextNode]) {
                q.emplace(nextNode, dist + weight);
                visited[nextNode] = true;
            }
        }
    }
    return {bestNode, maxDist};
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    graph.resize(N + 1);
    for (int i = 0; i < N - 1; i++) {
        int u, v, w; cin >> u >> v >> w;
        graph[u].emplace_back(v, w);
        graph[v].emplace_back(u, w);
    }

    auto [nodeA, distA] = bfs(1, 0);
    auto [nodeB, distB] = bfs(nodeA, 0);

    int ans = 0;

    auto [nodeC, distC] = bfs(1, nodeA);
    auto [nodeD, distD] = bfs(nodeC, nodeA);
    ans = max(ans, distD);

    auto [nodeE, distE] = bfs(1, nodeB);
    auto [nodeF, distF] = bfs(nodeE, nodeB);
    ans = max(ans, distF);
    
    cout << ans;
    return 0;
}