#include <iostream>
#include <vector>
using namespace std;


vector<vector<int>> graph;
vector<int> height;

int dfs(int curr, int parent) {
    int maxH = 0;
    for (int &child : graph[curr]) {
        if (child != parent) {
            maxH = max(maxH, dfs(child, curr) + 1);
        }
    }
    return height[curr] = maxH;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, s, d; cin >> n >> s >> d;
    graph.resize(n+1);
    for (int i = 1; i < n; i++) {
        int x, y; cin >> x >> y;
        graph[x].push_back(y);
        graph[y].push_back(x);
    }

    height.resize(n+1);
    dfs(s, 0);
    int numVisit = 0;
    for (int i = 1; i <= n; i++) {
        if (height[i] >= d) {
            numVisit++;
        }
    }
    int ans = 2 * numVisit - 2;
    cout << (ans >= 0 ? ans : 0);
}