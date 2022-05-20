#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;

typedef struct edge {
    int x, y, w;
} edge;

const int ROOT = 1;
int n, m;
vector<int> roots;
vector<vector<int>> graph;
vector<edge> edges;
vector<pi> edgeMST;

int findRoot(int x) {
    if (roots[x] == x)
        return x;
    return roots[x] = findRoot(roots[x]);
}
void merge(int rootA, int rootB) {
    if (rootA > rootB)
        swap(rootA, rootB);
    roots[rootB] = rootA;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    cin >> n >> m;
    roots.reserve(n + 1);
    for (int i = 0; i <= n; i++)
        roots[i] = i;
    int x, y;
    int rootA, rootB;
    for (int i = 0; i != m; i++) {
        cin >> x >> y;
        rootA = findRoot(x);
        rootB = findRoot(y);
        if (rootA != rootB)
            merge(rootA, rootB);
    }
    graph.resize(n, vector<int>(n, 0));
    for (int i = 0; i != n; i++)
        for (int j = 0; j != n; j++)
            cin >> graph[i][j];

    edges.reserve(n * (n - 1) / 2);
    for (int i = 1; i != n; i++)
        for (int j = 1; j != i; j++)
            edges.push_back({i + 1, j + 1, graph[i][j]});
            
    sort(edges.begin(), edges.end(), [](edge& a, edge& b) {return a.w < b.w;});

    int lenMST = 0;
    for (edge& e : edges) {
        rootA = findRoot(e.x);
        rootB = findRoot(e.y);
        if (rootA != rootB) {
            merge(rootA, rootB);
            lenMST += e.w;
            edgeMST.push_back({e.x, e.y});
        }
    }
    cout << lenMST << ' ' << edgeMST.size() << '\n';
    for (pi& e : edgeMST)
        cout << e.first << ' ' << e.second << '\n';
}