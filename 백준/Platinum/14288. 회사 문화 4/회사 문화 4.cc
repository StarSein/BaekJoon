#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

const int MAX_N = 1e5+1;
ll arr[MAX_N], seg[4*MAX_N], lazy[4*MAX_N], rseg[4*MAX_N];
int lb[MAX_N], ub[MAX_N];
vector<vector<int>> vChild;
int cnt = 0;

void dfs(int node) {
    lb[node] = ++cnt;
    for (int& child : vChild[node]) {
        dfs(child);
    }
    ub[node] = cnt;
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

void updateSEG(int node, int s, int e, int l, int r, int v) {
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
    updateSEG(2*node, s, mid, l, r, v);
    updateSEG(2*node+1, mid+1, e, l, r, v);
    seg[node] = seg[2*node] + seg[2*node+1];
}

ll querySEG(int node, int s, int e, int x) {
    updateLazy(node, s, e);
    if (s > x || e < x) {
        return 0;
    }
    if (s == x && e == x) {
        return seg[node];
    }
    int mid = (s + e) >> 1;
    return querySEG(2*node, s, mid, x) + querySEG(2*node+1, mid+1, e, x);
}

void updateRSEG(int node, int s, int e, int x, int v) {
    if (s > x || e < x) {
        return;
    }
    if (s == x && e == x) {
        rseg[node] += v;
        return;
    }
    int mid = (s + e) >> 1;
    updateRSEG(2*node, s, mid, x, v);
    updateRSEG(2*node+1, mid+1, e, x, v);
    rseg[node] = rseg[2*node] + rseg[2*node+1];
}

ll queryRSEG(int node, int s, int e, int l, int r) {
    if (s > r || e < l) {
        return 0;
    }
    if (l <= s && e <= r) {
        return rseg[node];
    }
    int mid = (s + e) >> 1;
    return queryRSEG(2*node, s, mid, l, r) + queryRSEG(2*node+1, mid+1, e, l, r);
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
    bool isTopDown = true;
    int type, i, w;
    for (int q = 0; q < m; q++) {
        cin >> type;
        if (type == 1) {
            cin >> i >> w;
            if (isTopDown) {
                updateSEG(1, 1, n, lb[i], ub[i], w);
            } else {
                updateRSEG(1, 1, n, lb[i], w);
            }
        } else if (type == 2) {
            cin >> i;
            cout << querySEG(1, 1, n, lb[i]) + queryRSEG(1, 1, n, 1, ub[i]) - queryRSEG(1, 1, n, 1, lb[i]-1) << '\n';
        } else {
            isTopDown = !isTopDown;
        }
    }
}