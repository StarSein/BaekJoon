#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX_N = 1e5+1;
int arr[MAX_N];
vector<int> seg[4*MAX_N];

void init(int node, int s, int e) {
    if (s == e) {
        seg[node].push_back(arr[s]);
        return;
    }
    int mid = (s + e) >> 1;
    init(node<<1, s, mid);
    init(node<<1|1, mid+1, e);
    seg[node].resize(seg[node<<1].size() + seg[node<<1|1].size());
    merge(seg[node<<1].begin(), seg[node<<1].end(), seg[node<<1|1].begin(), seg[node<<1|1].end(), seg[node].begin());
}

int query(int node, int s, int e, int l, int r, int v) {
    if (s > r || e < l) {
        return 0;
    }
    if (l <= s && e <= r) {
        return seg[node].size() - (upper_bound(seg[node].begin(), seg[node].end(), v) - seg[node].begin());
    }
    int mid = (s + e) >> 1;
    return query(node<<1, s, mid, l, r, v) + query(node<<1|1, mid+1, e, l, r, v);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }
    init(1, 1, n);
    int m; cin >> m;
    int a, b, c, answer = 0;
    for (int i = 0; i < m; i++) {
        cin >> a >> b >> c;
        answer = query(1, 1, n, a ^ answer, b ^ answer, c ^ answer);
        cout << answer << '\n';
    }
}