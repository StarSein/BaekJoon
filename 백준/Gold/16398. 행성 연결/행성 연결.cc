#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
using namespace std;
typedef tuple<int, int, int> ti;

vector<ti> vEdge;
vector<int> roots;

int find(int x) {
    if (roots[x] == x)
        return x;
    return roots[x] = find(roots[x]);
}

void merge(int a, int b) {
    if (a > b)
        swap(a, b);
    roots[b] = a;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    int cost;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> cost;
            vEdge.emplace_back(cost, i, j);
        }
    }
    sort(vEdge.begin(), vEdge.end(), less<ti>());
    roots.resize(n, 0);
    for (int i = 0; i < n; i++)
        roots[i] = i;
    
    long long lenMST = 0;
    int i, j, rootI, rootJ;
    for (ti& e : vEdge) {
        tie(cost, i, j) = e;
        rootI = find(i);
        rootJ = find(j);
        if (rootI != rootJ) {
            merge(rootI, rootJ);
            lenMST += cost;
        }
    }
    cout << lenMST;
}