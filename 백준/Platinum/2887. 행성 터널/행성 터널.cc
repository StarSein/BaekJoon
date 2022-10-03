#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
typedef long long ll;

struct Point {
    int id, x, y, z;
};

struct Edge {
    int a, b, w;
};

vector<Point> points;
vector<Edge> edges;
vector<int> roots;

int getRoot(int node) {
    if (roots[node] != node) {
        roots[node] = getRoot(roots[node]);
    }
    return roots[node];
}

void merge(int rootA, int rootB) {
    if (rootA > rootB) {
        swap(rootA, rootB);
    }
    roots[rootB] = rootA;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    points.reserve(N);
    for (int i = 0; i < N; i++) {
        int x, y, z; cin >> x >> y >> z;
        points.push_back({i, x, y, z});
    }
    edges.reserve(3 * N);
    sort(points.begin(), points.end(), [](Point &a, Point &b) {return a.x < b.x;});
    for (int i = 1; i < N; i++) {
        edges.push_back({points[i-1].id, points[i].id, points[i].x - points[i-1].x});
    }
    sort(points.begin(), points.end(), [](Point &a, Point &b) {return a.y < b.y;});
    for (int i = 1; i < N; i++) {
        edges.push_back({points[i-1].id, points[i].id, points[i].y - points[i-1].y});
    }
    sort(points.begin(), points.end(), [](Point &a, Point &b) {return a.z < b.z;});
    for (int i = 1; i < N; i++) {
        edges.push_back({points[i-1].id, points[i].id, points[i].z - points[i-1].z});
    }

    sort(edges.begin(), edges.end(), [](Edge &n, Edge &m) {return n.w < m.w;});
    roots.resize(N);
    for (int i = 0; i < N; i++) {
        roots[i] = i;
    }
    ll ans = 0;
    for (auto &[a, b, w] : edges) {
        int ra = getRoot(a);
        int rb = getRoot(b);
        if (ra != rb) {
            merge(ra, rb);
            ans += w;
        }
    }
    cout << ans;
    return 0;
}