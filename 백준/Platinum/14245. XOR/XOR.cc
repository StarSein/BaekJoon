#include <iostream>
using namespace std;

const int MAX_N = 5e5+1;
int arr[MAX_N], seg[4*MAX_N], lazy[4*MAX_N];

void makeSegTree(int node, int s, int e) {
    if (s == e) {
        seg[node] = arr[s];
        return;
    }
    int mid = (s + e) >> 1;
    makeSegTree(node << 1, s, mid);
    makeSegTree(node << 1 | 1, mid+1, e);
    seg[node] = seg[node << 1] ^ seg[node << 1 | 1];
}

void updateLazy(int node, int s, int e) {
    if (lazy[node]) {
        seg[node] ^= (e - s + 1) & 1 ? lazy[node] : 0;
        if (s != e) {
            lazy[node << 1] ^= lazy[node];
            lazy[node << 1 | 1] ^= lazy[node];
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
    updateRange(node << 1, s, mid, l, r, v);
    updateRange(node << 1 | 1, mid+1, e, l, r, v);
    seg[node] = seg[node << 1] ^ seg[node << 1 | 1];
}

int query(int node, int s, int e, int x) {
    updateLazy(node, s, e);

    if (s > x || e < x) {
        return 0;
    }
    if (s == x && e == x) {
        return seg[node];
    }
    int mid = (s + e) >> 1;
    return query(node << 1, s, mid, x) ^ query(node << 1 | 1, mid+1, e, x);
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

    int m; cin >> m;
    int t, a, b, c;
    for (int i = 0; i < m; i++) {
        cin >> t;
        if (t == 1) {
            cin >> a >> b >> c;
            updateRange(1, 1, n, a+1, b+1, c);
        } else {
            cin >> a;
            cout << query(1, 1, n, a+1) << '\n';
        }
    }
}