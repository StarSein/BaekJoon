#include <iostream>
#include <vector>
using namespace std;

vector<int> arr, bitree, lb, ub;
vector<vector<int>> vChild;

int n, m;
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

int sum(int i) {
    int ret = 0;
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
    arr.resize(n+1);
    bitree.resize(n+1);
    lb.resize(n+1);
    ub.resize(n+1);
    vChild.resize(n+1, vector<int>());
    int parent;
    cin >> parent;
    for (int i = 2; i <= n; i++) {
        cin >> parent;
        vChild[parent].push_back(i);
    }
    dfs(1);
    int cmd, i, w;
    for (int q = 0; q < m; q++) {
        cin >> cmd >> i;
        if (cmd == 1) {
            cin >> w;
            rangeUpdate(lb[i], ub[i], w);
        } else {
            cout << sum(lb[i]) << '\n';
        }
    }
}