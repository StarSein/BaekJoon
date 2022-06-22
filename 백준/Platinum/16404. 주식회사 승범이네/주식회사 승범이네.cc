#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

const int MAX_N = 1e5+1;
ll seg[4*MAX_N], lazy[4*MAX_N];
int lb[MAX_N], ub[MAX_N];
int cnt = 0;
vector<vector<int>> vChild;

void dfs(int curNode) {
    lb[curNode] = ++cnt;
    for (int& child : vChild[curNode]) {
        dfs(child);
    }
    ub[curNode] = cnt;
}

void updateLazy(int node, int s, int e) {
    if (lazy[node]) {
        seg[node] += (e - s + 1) * lazy[node];
        if (s != e) {
            lazy[2*node] += lazy[node];
            lazy[2*node+1] += lazy[node];
        }
        lazy[node] = 0;
    }
}

void update(int node, int s, int e, int l, int r, int v) {
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
    update(2*node, s, mid, l, r, v);
    update(2*node+1, mid+1, e, l, r, v);
    seg[node] = seg[2*node] + seg[2*node+1];
}

ll query(int node, int s, int e, int x) {
    updateLazy(node, s, e);
    if (s > x || e < x) {
        return 0;
    }
    if (s == x && e == x) {
        return seg[node];
    }
    int mid = (s + e) >> 1;
    return query(2*node, s, mid, x) + query(2*node+1, mid+1, e, x);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m; cin >> n >> m;
    vChild.resize(n+1, vector<int>());
    int parent; cin >> parent;
    for (int i = 2; i <= n; i++) {
        cin >> parent;
        vChild[parent].push_back(i);
    }
    dfs(1);

    int type, i, w;
    for (int q = 0; q < m; q++) {
        cin >> type;
        if (type == 1) {
            cin >> i >> w;
            update(1, 1, n, lb[i], ub[i], w);
        } else {
            cin >> i;
            cout << query(1, 1, n, lb[i]) << '\n';
        }
    }
}