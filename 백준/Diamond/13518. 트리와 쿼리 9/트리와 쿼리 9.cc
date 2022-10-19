#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <iterator>
using namespace std;

struct Query {
    int idx, lca, left, right;
};

const int MAX_N = 1e5 + 1;
const int MAX_NUM = 1e6 + 1;
const int LOG = 18;
int N, M;
int weights[MAX_N];
vector<vector<int>> graph;

int arr[2 * MAX_N];
int in[MAX_N];
int out[MAX_N];
int depth[MAX_N];
int table[MAX_N][LOG];
bool check[MAX_N];
int cnt[MAX_NUM];
int visitCnt = 0;
int curAns = 0;

Query qArr[MAX_N];
int ansArr[MAX_N];

void EulerRoute(int cur, int par) {
    in[cur] = ++visitCnt;
    arr[visitCnt] = cur;
    for (int &child : graph[cur]) {
        if (child != par) {
            EulerRoute(child, cur);
        }
    }
    out[cur] = ++visitCnt;
    arr[visitCnt] = cur;
}

void dfs(int cur, int par) {
    depth[cur] = depth[par] + 1;
    table[cur][0] = par;
    for (int &child : graph[cur]) {
        if (child != par) {
            dfs(child, cur);
        }
    }
}

int getLCA(int a, int b) {
    int diff = depth[b] - depth[a];
    for (int i = LOG - 1; i >= 0; i--) {
        if (diff >= 1 << i) {
            diff -= 1 << i;
            b = table[b][i];
        }
    }

    if (a == b) {
        return a;
    }

    for (int i = LOG - 1; i >= 0; i--) {
        if (table[a][i] != table[b][i]) {
            a = table[a][i];
            b = table[b][i];
        }
    }
    a = table[a][0];
    return a;
}

void include(int s, int e) {
    for (int i = s; i <= e; i++) {
        int node = arr[i];
        int w = weights[node];
        if (check[node]) {
            check[node] = false;
            cnt[w]--;
            if (cnt[w] == 0) {
                curAns--;
            }
        } else {
            check[node] = true;
            cnt[w]++;
            if (cnt[w] == 1) {
                curAns++;
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> weights[i];
    }
    graph.resize(N + 1);
    for (int i = 0; i < N - 1; i++) {
        int u, v; cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    EulerRoute(1, 0);

    dfs(1, 0);
    for (int i = 1; i < LOG; i++) {
        for (int node = 1; node <= N; node++) {
            int mid = table[node][i - 1];
            table[node][i] = table[mid][i - 1];
        }
    }

    cin >> M;
    for (int i = 0; i < M; i++) {
        int u, v; cin >> u >> v;
        if (depth[u] > depth[v]) {
            swap(u, v);
        }
        int p = getLCA(u, v);
        if (p == u) {
            qArr[i] = {i, 0, in[u], in[v]};
        } else {
            qArr[i] = {i, p, out[u], in[v]};
        }
    }
    const int SQRT = sqrt(2 * N);
    sort(qArr, qArr + M, [&](Query &a, Query &b) {return a.left/SQRT != b.left/SQRT ? a.left/SQRT < b.left/SQRT : a.right < b.right;});

    int curL = 1, curR = 0;
    for (int i = 0; i < M; i++) {
        Query &q = qArr[i];
        
        if (q.right > curR) include(curR + 1, q.right);
        else                include(q.right + 1, curR);
        if (q.left < curL)  include(q.left, curL - 1);
        else                include(curL, q.left - 1);

        curL = q.left;
        curR = q.right;
        ansArr[q.idx] = curAns;
        if (q.lca) {
            if (cnt[weights[q.lca]] == 0) {
                ansArr[q.idx]++;
            }
        }
    }
    copy(ansArr, ansArr + M, ostream_iterator<int>(cout, "\n"));
    return 0;
}