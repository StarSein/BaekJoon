#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

const int LOG = 17;
vector<int> depthVec;
vector<vector<pi>> graph;
vector<vector<pll>> table;


void dfs(int currentNode, int parentNode, int depth) {
    depthVec[currentNode] = depth;
    for (auto &[nextNode, weight] : graph[currentNode]) {
        if (nextNode != parentNode) {
            table[nextNode][0] = {currentNode, weight};
            dfs(nextNode, currentNode, depth + 1);
        }
    }
}


pll getAncestor(int node, int step) {
    int exp = LOG - 1;
    int bit = 1 << exp;
    ll cost = 0;
    while (bit) {
        if (table[node][exp].first != -1 && (step & bit)) {
            cost += table[node][exp].second;
            node = table[node][exp].first;
        }
        exp--;
        bit >>= 1;
    }
    return {node, cost};
}

pll getLCA(int u, int v) {
    ll cost = 0;
    while (u != v) {
        int exp = LOG - 1;
        while (exp && (table[u][exp].first == -1 || table[u][exp].first == table[v][exp].first)) {
            exp--;
        }
        
        cost += table[u][exp].second;
        cost += table[v][exp].second;
        u = table[u][exp].first;
        v = table[v][exp].first;
    }
    return {u, cost};
}

ll queryOne(int u, int v) {
    if (depthVec[u] > depthVec[v]) {
        swap(u, v);
    }
    auto [nv, cost] = getAncestor(v, depthVec[v] - depthVec[u]);
    if (u == nv) {
        return cost;
    }
    cost += getLCA(u, nv).second;
    return cost;
}

ll queryTwo(int u, int v, int k) {
    if (k == 0) {
        return u;
    }
    if (depthVec[u] > depthVec[v]) {
        swap(u, v);
        k *= -1;
    }
    ll nv = getAncestor(v, depthVec[v] - depthVec[u]).first;
    if (u == nv) {
        if (k > 0) {
            return getAncestor(v, depthVec[v] - depthVec[u] - k).first;
        } else {
            return getAncestor(v, -k).first;
        }
    }
    ll lca = getLCA(u, nv).first;
    int dist = depthVec[u] + depthVec[v] - 2 * depthVec[lca];
    if (k < 0) {
        swap(u, v);
        k *= -1;
    }
    if (k <= depthVec[u] - depthVec[lca]) {
        return getAncestor(u, k).first;
    } else {
        return getAncestor(v, dist - k).first;
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

    depthVec.resize(N + 1);
    table.resize(N + 1, vector<pll>(LOG, {-1, -1}));
    dfs(1, 0, 0);
    for (int exp = 1; exp < LOG; exp++) {
        for (int node = 1; node <= N; node++) {
            auto &[midNode, midLength] = table[node][exp-1];
            if (midNode == -1) {
                continue;
            }
            auto &[endNode, endLength] = table[midNode][exp-1];
            if (endNode == -1) {
                continue;
            }
            table[node][exp] = {endNode, midLength + endLength};
        }
    }

    int M; cin >> M;
    vector<ll> vec;
    vec.reserve(M);
    for (int i = 0; i < M; i++) {
        int q, u, v, k;
        cin >> q;
        if (q == 1) {
            cin >> u >> v;
            vec.push_back(queryOne(u, v));
        } else {
            cin >> u >> v >> k;
            vec.push_back(queryTwo(u, v, k - 1));
        }
    }

    for (ll &e : vec) {
        cout << e << '\n';
    }
    return 0;
}