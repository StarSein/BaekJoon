#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_set>
#include <numeric>
using namespace std;
typedef pair<int, int> pi;
typedef long long ll;

const ll INF = 5e10;
const int MAX_V = 5e4 + 1;
const int EXP = 16;

struct Edge {
    int idx, u, v, w;

    Edge() = default;
    Edge(int idx, int u, int v, int w): idx(idx), u(u), v(v), w(w) {};
};

struct Info {
    int node, fw, sw;
};

int V, E;
vector<vector<pi>> graph;
vector<vector<pi>> tree;
vector<Edge> edges;
vector<int> roots;
vector<int> depths;
vector<bool> contained;
Info parent[MAX_V][EXP];

int findRoot(int x) {
    if (roots[x] != x) {
        roots[x] = findRoot(roots[x]);
    }
    return roots[x];
}

void merge(int a, int b) {
    if (a > b) {
        swap(a, b);
    }
    roots[b] = a;
}

void dfs(int cur, int par) {
    depths[cur] = depths[par] + 1;
    for (const auto &[nex, w] : tree[cur]) {
        if (nex != par) {
            parent[nex][0] = {cur, w, -1};
            dfs(nex, cur);
        }
    }
}

int maxW(int a, int b, int w) {
    unordered_set<int> weights;
    if (depths[a] > depths[b]) {
        swap(a, b);
    }

    int diff = depths[b] - depths[a];
    for (int i = EXP - 1; i >= 0 && diff; i--) {
        if (diff >= (1 << i)) {
            weights.insert(parent[b][i].fw);
            weights.insert(parent[b][i].sw);
            b = parent[b][i].node;
            diff -= (1 << i);
        }
    }

    if (a != b) {
        for (int i = EXP - 1; i >= 0; i--) {
            if (parent[a][i].node != parent[b][i].node) {
                weights.insert(parent[a][i].fw);
                weights.insert(parent[a][i].sw);
                weights.insert(parent[b][i].fw);
                weights.insert(parent[b][i].sw);
                a = parent[a][i].node;
                b = parent[b][i].node;
            }
        }
        weights.insert(parent[a][0].fw);
        weights.insert(parent[a][0].sw);
        weights.insert(parent[b][0].fw);
        weights.insert(parent[b][0].sw);
    }

    auto mw = *max_element(weights.begin(), weights.end());
    if (mw == w) {
        weights.erase(mw);
        mw = *max_element(weights.begin(), weights.end());
    }
    return mw;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> V >> E;
    graph.resize(V + 1);
    edges.reserve(E);
    int u, v, w;
    for (int i = 0; i < E; i++) {
        cin >> u >> v >> w;
        graph[u].emplace_back(v, w);
        graph[v].emplace_back(u, w);
        edges.emplace_back(i, u, v, w);
    }

    roots.resize(V + 1);
    for (int i = 1; i <= V; i++) {
        roots[i] = i;
    }

    sort(edges.begin(), edges.end(), [](Edge &a, Edge &b) {return a.w < b.w;});

    contained.resize(E, false);
    tree.resize(V + 1);
    int mstLen = 0;
    for (const Edge &e : edges) {
        int ru = findRoot(e.u);
        int rv = findRoot(e.v);
        if (ru != rv) {
            contained[e.idx] = true;
            mstLen += e.w;
            merge(ru, rv);
            tree[e.u].emplace_back(e.v, e.w);
            tree[e.v].emplace_back(e.u, e.w);
        }
    }

    int cpr = findRoot(1);
    for (int i = 2; i <= V; i++) {
        if (findRoot(i) != cpr) {
            cout << -1;
            return 0;
        }
    }

    depths.resize(V + 1);
    dfs(1, 0);
    for (int i = 1; i < EXP; i++) {
        for (int j = 1; j <= V; j++) {
            const Info &mid = parent[j][i - 1];
            const Info &end = parent[mid.node][i - 1];
            int fw = max(mid.fw, end.fw);
            int sw = mid.sw;
            if (mid.fw < fw && mid.fw > sw) {
                sw = mid.fw;
            }
            if (end.fw < fw && end.fw > sw) {
                sw = end.fw;
            }
            parent[j][i] = {end.node, fw, sw};
        }
    }

    ll ans = INF;
    for (const Edge &e : edges) {
        if (contained[e.idx]) {
            continue;
        }

        int deleteW = maxW(e.u, e.v, e.w);
        if (deleteW == -1) {
            continue;
        }
        ll cur = mstLen + e.w - deleteW;
        if (cur > mstLen) {
            ans = min(ans, cur);
        }
    }

    if (ans == INF) {
        cout << -1;
    } else {
        cout << ans;
    }
    return 0;
}