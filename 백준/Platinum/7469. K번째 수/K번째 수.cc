#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;

const int SIZE = 1e5+1;
int arr[SIZE];
vector<int> seg[4*SIZE];

int n, m;

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

int query(int node, int s, int e, int l, int r, int x) {
    if (s > r || e < l) {
        return 0;
    }
    if (l <= s && e <= r) {
        return upper_bound(seg[node].begin(), seg[node].end(), x) - seg[node].begin();
    }
    int mid = (s + e) >> 1;
    return query(node<<1, s, mid, l, r, x) + query(node<<1|1, mid+1, e, l, r, x);
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }
    init(1, 1, n);
    int i, j, k;
    ll left, right, mid;
    while (m--) {
        cin >> i >> j >> k;
        left = -1e9;
        right = 1e9;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (query(1, 1, n, i, j, mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        cout << left << '\n';
    }
}