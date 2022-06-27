#include <iostream>
#include <vector>
using namespace std;

const int MAX_N = 1e5+1, ROOT = 1;
int arr[MAX_N], seg[4*MAX_N], lazy[4*MAX_N];
int lb[MAX_N], ub[MAX_N];
vector<vector<int>> graph;
int cnt = 0;
vector<int> answer;


void dfs(int curr, int prev) {
    lb[curr] = ++cnt;
    for (int& next : graph[curr]) {
        if (next != prev) {
            dfs(next, curr);
        }
    }
    ub[curr] = cnt;
}

void makeSegTree(int node, int s, int e) {
    if (s == e) {
        seg[node] = arr[s];
        return;
    }
    int mid = (s + e) >> 1;
    makeSegTree(node<<1, s, mid);
    makeSegTree(node<<1|1, mid+1, e);
    seg[node] = seg[node<<1] ^ seg[node<<1|1];
}

void updateLazy(int node, int s, int e) {
    if (lazy[node]) {
        seg[node] ^= (e - s + 1 & 1 ? lazy[node] : 0);
        if (s != e) {
            lazy[node<<1] ^= lazy[node];
            lazy[node<<1|1] ^= lazy[node];
        }
        lazy[node] = 0;
    }
}

void updateRange(int node, int s, int e, int l, int r, int v) {
    updateLazy(node, s, e);

    if (s > r || e < l) {
        return;
    }
    if (l <= s && e <= r) {
        lazy[node] = v;
        updateLazy(node, s, e);
        return;
    }
    int mid = (s + e) >> 1;
    updateRange(node<<1, s, mid, l, r, v);
    updateRange(node<<1|1, mid+1, e, l, r, v);
    seg[node] = seg[node<<1] ^ seg[node<<1|1];
}

int query(int node, int s, int e, int l, int r) {
    updateLazy(node, s, e);

    if (s > r || e < l) {
        return 0;
    }
    if (l <= s && e <= r) {
        return seg[node];
    }
    int mid = (s + e) >> 1;
    return query(node<<1, s, mid, l, r) ^ query(node<<1|1, mid+1, e, l, r);
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m; cin >> n >> m;
    graph.resize(n+1, vector<int>());
    int a, b;
    for (int i = 1; i < n; i++) {
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    dfs(ROOT, 0);
    for (int i = 1; i <= n; i++) {
        cin >> arr[lb[i]];
    }

    makeSegTree(1, 1, n);
    int q, x, y;
    for (int i = 0; i < m; i++) {
        cin >> q;
        if (q == 1) {
            cin >> x;
            
            answer.push_back(query(1, 1, n, lb[x], ub[x]));
        } else {
            cin >> x >> y;

            updateRange(1, 1, n, lb[x], ub[x], y);
        }
    }
    for (int a : answer) {
        cout << a << '\n';
    }
}