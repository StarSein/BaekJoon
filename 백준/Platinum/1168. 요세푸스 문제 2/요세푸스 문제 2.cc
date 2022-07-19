#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int SZ = 1e5+1;
int seg[4*SZ];

int n, k;
vector<int> ans;

void init(int node, int s, int e) {
    if (s == e) {
        seg[node] = 1;
        return;
    }
    int mid = (s + e) >> 1;
    init(node<<1, s, mid);
    init(node<<1|1, mid+1, e);
    seg[node] = seg[node<<1] + seg[node<<1|1];
}

int find(int node, int s, int e, int target) {
    if (s == e) {
        return s;
    }
    int mid = (s + e) >> 1;
    if (target <= seg[node<<1]) {
        return find(node<<1, s, mid, target);
    } else {
        return find(node<<1|1, mid+1, e, target-seg[node<<1]);
    }
}

void update(int node, int s, int e, int x) {
    if (x < s || x > e) {
        return;
    }
    if (s == x && x == e) {
        seg[node]--;
        return;
    }
    int mid = (s + e) >> 1;
    update(node<<1, s, mid, x);
    update(node<<1|1, mid+1, e, x);
    seg[node] = seg[node<<1] + seg[node<<1|1];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    int n, k; cin >> n >> k;
    init(1, 1, n);

    ans.reserve(n);
    int pos = 1;
    for (int i = n; i > 0; i--) {
        pos = (pos + k - 1) % i;
        if (pos == 0) {
            pos = i;
        }
        int num = find(1, 1, n, pos);
        update(1, 1, n, num);
        ans.push_back(num);
    }
    cout << '<' << ans[0];
    for (int i = 1; i < n; i++) {
        cout << ", " << ans[i];
    }
    cout << '>';
}