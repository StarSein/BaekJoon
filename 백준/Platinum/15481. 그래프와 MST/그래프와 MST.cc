#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;
typedef pair<int, int> pi;

struct Edge {
    int idx, u, v, w;

    Edge() = default;
    Edge(int idx, int u, int v, int w): idx(idx), u(u), v(v), w(w) {};
};

int N, M;
vector<vector<pi>> graph;
vector<vector<pi>> tree;
vector<int> roots;
vector<int> depths;
vector<pi> parents;
vector<Edge> edges;
vector<Edge> queries;
vector<bool> contained;

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
            parents[nex] = {cur, w};
            dfs(nex, cur);
        }
    }
}

int maxW(int a, int b) {
    int ret = 0;
    if (depths[a] > depths[b]) {
        swap(a, b);
    }
    int diff = depths[b] - depths[a];
    for (int i = 0; i < diff; i++, b = parents[b].first) {
        ret = max(ret, parents[b].second);
    }

    for (; a != b; a = parents[a].first, b = parents[b].first) {
        ret = max(ret, parents[a].second);
        ret = max(ret, parents[b].second);
    }

    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    graph.resize(N + 1);
    edges.reserve(M);
    queries.reserve(M);
    int u, v, w;
    for (int i = 0; i < M; i++) {
        cin >> u >> v >> w;
        graph[u].emplace_back(v, w);
        graph[v].emplace_back(u, w);
        edges.emplace_back(i, u, v, w);
        queries.emplace_back(i, u, v, w);
    }

    roots.resize(N + 1);
    for (int i = 1; i <= N; i++) {
        roots[i] = i;
    }

    sort(edges.begin(), edges.end(), [](Edge &a, Edge &b) {return a.w < b.w;});

    contained.resize(M, false);
    tree.resize(N + 1, vector<pi>());
    ll mstLen = 0;
    for (const Edge &e : edges) {
        int ru = findRoot(e.u);
        int rv = findRoot(e.v);
        if (ru != rv) {
            merge(ru, rv);
            mstLen += e.w;
            contained[e.idx] = true;
            tree[e.u].emplace_back(e.v, e.w);
            tree[e.v].emplace_back(e.u, e.w);
        }
    }

    depths.resize(N + 1);
    parents.resize(N + 1);
    dfs(1, 0);

    ll response = 0;
    for (const Edge &e : queries) {
        if (contained[e.idx]) {
            response = mstLen;
        } else {
            response = mstLen + e.w - maxW(e.u, e.v);
        }
        cout << response << '\n';
    }
    return 0;
}