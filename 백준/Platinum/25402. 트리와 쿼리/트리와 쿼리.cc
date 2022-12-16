#include <iostream>
#include <vector>
#include <unordered_set>
using namespace std;
typedef long long ll;


int N, Q;
vector<vector<int>> graph;
vector<int> tree, roots, sizes;

void makeTree(int cur, int par) {
    tree[cur] = par;
    for (int nex : graph[cur]) {
        if (nex != par) {
            makeTree(nex, cur);
        }
    }    
}

int findRoot(int node) {
    if (roots[node] != node) {
        roots[node] = findRoot(roots[node]);
    }
    return roots[node];
}

void merge(int a, int b) {
    if (a > b) {
        swap(a, b);
    }
    sizes[a] += sizes[b];
    roots[b] = roots[a];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    graph.resize(N + 1);
    tree.resize(N + 1);
    for (int a, b, i = 0; i < N - 1; i++) {
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    makeTree(1, 0);
    roots.resize(N + 1);
    sizes.resize(N + 1);
    cin >> Q;
    for (int k, i = 1; i <= Q; i++) {
        cin >> k;
        unordered_set<int> containedSet;
        for (int s, j = 0; j < k; j++) {
            cin >> s;
            containedSet.insert(s);
            roots[s] = s;
            sizes[s] = 1;
        }
        for (int cur : containedSet) {
            int par = tree[cur];
            if (containedSet.count(par)) {
                int rc = findRoot(cur);
                int rp = findRoot(par);
                merge(rc, rp);
            }
        }
        unordered_set<int> rootSet;
        for (int cur : containedSet) {
            rootSet.insert(findRoot(cur));
        }
        ll ans = 0;
        for (int r : rootSet) {
            ans += (ll)sizes[r] * (sizes[r] - 1) / 2;
        }
        cout << ans << '\n';
    }
    return 0;
}