#include <iostream>
#include <vector>
#include <stack>
#include <cmath>
using namespace std;
typedef long long ll;

const int MOD = 1e9+7;
vector<vector<int>> digraph, revgraph;
vector<bool> visit;
vector<int> scc;
stack<int> st;
vector<int> sccIdgr, sccSize;
int cnt = 0;

void dfs1(int curr) {
    visit[curr] = true;
    for (int &next : digraph[curr]) {
        if (!visit[next]) {
            dfs1(next);
        }
    }
    st.push(curr);
}

void dfs2(int curr) {
    visit[curr] = true;
    for (int &next : revgraph[curr]) {
        if (!visit[next]) {
            dfs2(next);
        }
    }
    scc[curr] = cnt;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int n; cin >> n;
    digraph.resize(n, vector<int>());
    revgraph.resize(n, vector<int>());
    bool connect;
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
            cin >> connect;
            if (r != c && connect) {
                digraph[r].push_back(c);
                revgraph[c].push_back(r);
            }
        }
    }
    visit.resize(n, false);
    for (int i = 0; i < n; i++) {
        if (!visit[i]) {
            dfs1(i);
        }
    }
    scc.resize(n);
    fill(visit.begin(), visit.end(), false);
    while (!st.empty()) {
        int curr = st.top();
        st.pop();
        if (!visit[curr]) {
            dfs2(curr);
            cnt++;
        }
    }
    sccIdgr.resize(cnt);
    sccSize.resize(cnt);
    for (int i = 0; i < n; i++) {
        sccSize[scc[i]]++;
    }
    for (int i = 0; i < n; i++) {
        if (sccIdgr[scc[i]]) {
            continue;
        }
        for (int &next : revgraph[i]) {
            if (digraph[next].size() == sccSize[scc[i]]) {
                sccIdgr[scc[i]]++;
            }
        }
    }
    ll ans = 1;
    for (int i = 0; i < sccIdgr.size(); i++) {
        ll expr = sccIdgr[i], base = sccSize[i];
        while (expr--) {
            ans = (ans * base) % MOD;
        }
        while (--base) {
            ans = (ans * base) % MOD;
        }
    }
    cout << ans;
}