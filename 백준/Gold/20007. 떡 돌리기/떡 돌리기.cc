#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;
#define INF 1e9

struct Adj {
    int node, weight;

    Adj() = default;
    Adj(int node, int weight): node(node), weight(weight) {};
};

int N, M, X, Y;
vector<vector<Adj>> graph;
vector<int> dist;

void dijkstra() {
    dist.resize(N, INF);
    struct Node {
        int node, cost;

        Node() = default;
        Node(int node, int cost): node(node), cost(cost) {};
    };
    struct cmp {
        bool operator() (Node &a, Node &b) {
            return a.cost > b.cost;
        }
    };
    priority_queue<Node, vector<Node>, cmp> pq;

    pq.emplace(Y, 0);
    while (!pq.empty()) {
        Node cur = pq.top();
        pq.pop();
        if (dist[cur.node] != INF) {
            continue;
        }
        dist[cur.node] = cur.cost;

        for (Adj &e : graph[cur.node]) {
            if (dist[e.node] == INF) {
                pq.emplace(e.node, cur.cost + e.weight);
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M >> X >> Y;
    graph.resize(N);
    for (int i = 0; i < M; i++) {
        int a, b, c; cin >> a >> b >> c;
        graph[a].emplace_back(b, c);
        graph[b].emplace_back(a, c);
    }

    dijkstra();

    sort(dist.begin(), dist.end());
    for (int &x : dist) {
        x *= 2;
    }
    int sum = 0;
    int ans = 1;
    for (int i = 0; i < dist.size(); ++i) {
        if (sum + dist[i] <= X) {
            sum += dist[i];
        } else if (dist[i] > X) {
            ans = -1;
            break;
        } else {
            ans++;
            sum = dist[i];
        }
    }
    cout << ans;
    return 0;
}