#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;
typedef long long ll;

const int MAX_N = 1e5 + 1;
const int LOG = 17;

ll depth[MAX_N], cost[MAX_N];
ll table[MAX_N][LOG];
vector<vector<pi>> graph;
vector<ll> ansVec;

void dfs(int curNode, int parNode, int d) {
    depth[curNode] = d;
    for (auto &[nextNode, weight] : graph[curNode]) {
        if (nextNode != parNode) {
            table[nextNode][0] = curNode;
            cost[nextNode] = cost[curNode] + weight;
            dfs(nextNode, curNode, d + 1);
        }
    }
}

int getAncestor(int node, int step) {
    for (int exp = LOG - 1, bit = 1 << exp; bit > 0; exp--, bit >>= 1) {
        if (step & bit) {
            node = table[node][exp];
        }
    }
    return node;
}

int getLCA(int u, int v) {
    if (depth[u] > depth[v]) {
        swap(u, v);
    }
    v = getAncestor(v, depth[v] - depth[u]);

    if (u != v) {
        for (int exp = LOG - 1, bit = 1 << exp; bit > 0; exp--, bit >>= 1) {
            if (table[u][exp] != table[v][exp]) {
                u = table[u][exp];
                v = table[v][exp];
            }
        }
        u = table[u][0];
    }
    return u;
}

ll queryOne(int u, int v) {
    int lca = getLCA(u, v);
    return cost[u] + cost[v] - 2 * cost[lca];
}

ll queryTwo(int u, int v, int k) {
    int lca = getLCA(u, v);
    int diffU = depth[u] - depth[lca];
    int diffV = depth[v] - depth[lca];
    
    if (k <= diffU) {
        return getAncestor(u, k);
    } else {
        return getAncestor(v, diffU + diffV - k);
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    graph.resize(N + 1);
    for (int i = 0; i < N - 1; i++) {
        int u, v, w; cin >> u >> v >> w;
        graph[u].emplace_back(v, w);
        graph[v].emplace_back(u, w);
    }

    dfs(1, 0, 0);
    for (int exp = 1; exp < LOG; exp++) {
        for (int node = 1; node <= N; node++) {
            table[node][exp] = table[table[node][exp-1]][exp-1];
        }
    }
    int M; cin >> M;
    for (int i = 0; i < M; i++) {
        int q, u, v, k;
        cin >> q;
        if (q == 1) {
            cin >> u >> v;
            cout << queryOne(u, v) << '\n';
        } else {
            cin >> u >> v >> k;
            cout << queryTwo(u, v, k - 1) << '\n';
        }
    }
    return 0;
}