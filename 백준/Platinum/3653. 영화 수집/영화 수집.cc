#include <iostream>
#include <cstring>
using namespace std;


const int MAX_N = 1e5+1;
int tree[2*MAX_N], node[MAX_N];

void update(int i, int v) {
    while (i < size(tree)) {
        tree[i] += v;
        i += (i & -i);
    }
}

int query(int i) {
    int ret = 0;
    while (i) {
        ret += tree[i];
        i -= (i & -i);
    }
    return ret;
}

void init(int n) {
    memset(tree, 0, sizeof(tree));
    for (int i = 1; i <= n; i++) {
        update(i, 1);
    }
    for (int i = 1; i <= n; i++) {
        node[i] = n + 1 - i;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int t; cin >> t;
    while (t--) {
        int n, m; cin >> n >> m;
        
        init(n);
        for (int i = 1; i <= m; i++) {
            int num; cin >> num;
            cout << query(size(tree)-1) - query(node[num]) << '\n';
            update(node[num], -1);
            node[num] = n + i;
            update(node[num], 1);
        }
    }
}