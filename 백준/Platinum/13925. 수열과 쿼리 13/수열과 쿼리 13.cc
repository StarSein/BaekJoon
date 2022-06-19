#include <iostream>
#include <queue>
using namespace std;
typedef long long ll;

const int MAX_N = 1e5+1, MOD = 1e9+7;
ll arr[MAX_N], seg[4*MAX_N], lazy[4*MAX_N][2];

void makeSegTree(int node, int start, int end) {
    if (start == end) {
        seg[node] = arr[start];
        return;
    }
    int mid = (start + end) / 2;
    makeSegTree(2*node, start, mid);
    makeSegTree(2*node+1, mid+1, end);
    seg[node] = (seg[2*node] + seg[2*node+1]) % MOD;
}

void updateLazy(int node, int start, int end) {
    ll& m1 = lazy[node][0];
    ll& a1 = lazy[node][1]; 
    if (m1 == 1 && a1 == 0) {
        return;
    }
    seg[node] = (m1 * seg[node] + ((ll)end - start + 1) * a1) % MOD;
    if (start != end) {
        for (int i = 2*node; i <= 2*node+1; i++) {
            ll m2 = lazy[i][0], a2 = lazy[i][1];
            lazy[i][0] = (m1 * m2) % MOD;
            lazy[i][1] = (m1 * a2 + a1) % MOD;
        }
    }
    m1 = 1, a1 = 0;
}

void updateRange(int node, int start, int end, int l, int r, ll mult, ll add) {
    updateLazy(node, start, end);

    if (start > r || end < l) {
        return;
    }
    if (l <= start && end <= r) {
        lazy[node][0] = mult;
        lazy[node][1] = (mult * lazy[node][1] + add) % MOD;
        updateLazy(node, start, end);
        return;
    }
    int mid = (start + end) / 2;
    updateRange(2*node, start, mid, l, r, mult, add);
    updateRange(2*node+1, mid+1, end, l, r, mult, add);
    seg[node] = (seg[2*node] + seg[2*node+1]) % MOD;
}

ll query(int node, int start, int end, int l, int r) {
    updateLazy(node, start, end);

    if (start > r || end < l) {
        return 0;
    }
    if (l <= start && end <= r) {
        return seg[node];
    }
    int mid = (start + end) / 2;
    return query(2*node, start, mid, l, r) + query(2*node+1, mid+1, end, l, r);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }
    makeSegTree(1, 1, n);
    for (int i = 1; i <= 4 * n; i++) {
        lazy[i][0] = 1;
        lazy[i][1] = 0;
    }

    int m; cin >> m;
    int type, x, y, v;
    for (int i = 0; i < m; i++) {
        cin >> type;
        if (type == 1) {
            cin >> x >> y >> v;
            updateRange(1, 1, n, x, y, 1, v);
        } else if (type == 2) {
            cin >> x >> y >> v;
            updateRange(1, 1, n, x, y, v, 0);
        } else if (type == 3) {
            cin >> x >> y >> v;
            updateRange(1, 1, n, x, y, 0, v);
        } else {
            cin >> x >> y;
            cout << query(1, 1, n, x, y) % MOD << '\n';
        }
    }
}
