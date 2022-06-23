#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

int n, m;
vector<ll> initWage, arr, bitree;
vector<vector<int>> vChild;
vector<int> lb, ub;
int cnt = 0;

void dfs(int curr) {
    lb[curr] = ++cnt;
    for (int& child : vChild[curr]) {
        dfs(child);
    }
    ub[curr] = cnt;
}

void update(int i, int v) {
    while (i <= n) {
        bitree[i] += v;
        i += (i & -i);
    }
}

void rangeUpdate(int l, int r, int v) {
    update(l, v);
    update(r+1, -v);
}

ll sum(int i) {
    ll ret = 0;
    while (i > 0) {
        ret += bitree[i];
        i -= (i & -i);
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    initWage.resize(n+1);
    arr.resize(n+1);
    bitree.resize(n+1);
    vChild.resize(n+1, vector<int>());
    lb.resize(n+1);
    ub.resize(n+1);
    cin >> initWage[1];
    int parent;
    for (int i = 2; i <= n; i++) {
        cin >> initWage[i] >> parent;
        vChild[parent].push_back(i);
    }
    dfs(1);
    for (int i = 1; i <= n; i++) {
        arr[lb[i]] = initWage[i];
    }
    for (int i = 1; i <= n; i++) {
        update(i, arr[i] - arr[i-1]);
    }

    char cmd; int a, x;
    for (int i = 0; i < m; i++) {
        cin >> cmd >> a;
        if (cmd == 'p') {
            cin >> x;
            rangeUpdate(lb[a]+1, ub[a], x);
        } else {
            cout << sum(lb[a]) << '\n';
        }
    }
}