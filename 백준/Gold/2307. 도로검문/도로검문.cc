#include <iostream>
#include <queue>
#include <vector>
#include <cstring>
#include <tuple>
#include <set>
using namespace std;
#define INF 1e9
typedef pair<int, int> pi;
typedef tuple<int, int, int> ti;

int N, M;
vector<set<pi>> graph;
vector<ti> edges;
vector<int> pn;
set<pi> inRoute;

int dijkstra(int s, int e) {
    priority_queue<ti, vector<ti>, greater<ti>> pq;
    bool visited[N + 1];
    memset(visited, 0, sizeof(visited));

    pq.emplace(0, s, 0);
    while (!pq.empty()) {
        const auto [dist, curNode, prevNode] = pq.top();
        pq.pop();

        if (visited[curNode]) {
            continue;
        }
        visited[curNode] = true;
        pn[curNode] = prevNode;

        if (curNode == e) {
            return dist;
        }

        for (const auto &[nextNode, weight] : graph[curNode]) {
            if (!visited[nextNode]) {
                pq.emplace(dist + weight, nextNode, curNode);
            }
        }
    }
    return INF;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    graph.resize(N + 1);
    edges.reserve(M);
    for (int i = 0; i < M; ++i) {
        int a, b, t; cin >> a >> b >> t;
        graph[a].emplace(b, t);
        graph[b].emplace(a, t);
        edges.emplace_back(a, b, t);
    }

    pn.resize(N + 1);
    int minDist = dijkstra(1, N);
    if (minDist == INF) {
        cout << -1;
        return 0;
    }

    int node = N;
    while (pn[node]) {
        int a = node;
        int b = pn[node];
        if (a > b) swap(a, b);
        inRoute.emplace(a, b);
        node = pn[node];
    }

    int ans = 0;
    for (auto [a, b, t] : edges) {
        if (a > b) swap(a, b);
        if (inRoute.count(make_pair(a, b))) {
            graph[a].erase(make_pair(b, t));
            graph[b].erase(make_pair(a, t));
            int d = dijkstra(1, N);
            if (d == INF) {
                cout << -1;
                return 0;
            }
            ans = max(ans, d - minDist);
            graph[a].emplace(b, t);
            graph[b].emplace(a, t);
        }
    }
    cout << ans;
    return 0;
}