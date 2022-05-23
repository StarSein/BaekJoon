#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;
typedef pair<int, int> pi;
typedef pair<long long, int> pli;

vector<int> inSight;
vector<vector<pi>> graph;
vector<bool> visited;
priority_queue<pli, vector<pli>, greater<pli>> pq;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m; cin >> n >> m;
    inSight.resize(n, 0);
    for (int i = 0; i < n; i++)
        cin >> inSight[i];
    inSight[n-1] = 0;

    graph.resize(n, vector<pi>());
    int a, b, t;
    for (int i = 0; i < m; i++) {
        cin >> a >> b >> t;
        if (!inSight[a] && !inSight[b]) {
            graph[a].push_back({t, b});
            graph[b].push_back({t, a});
        }
    }

    int curNode, nextNode, weight;
    long long curTime, minTime = -1;
    visited.resize(n, false);
    pq.push({0, 0});
    while (!pq.empty()) {
        tie(curTime, curNode) = pq.top();
        pq.pop();
        if (visited[curNode])
            continue;
        visited[curNode] = true;
        if (curNode == n-1) {
            minTime = curTime;
            break;
        }
        for (pi& e : graph[curNode]) {
            tie(weight, nextNode) = e;
            if (!visited[nextNode])
                pq.push({curTime + weight, nextNode});
        }
    }
    cout << minTime;
}