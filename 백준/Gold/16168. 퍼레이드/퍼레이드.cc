#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

vector<int> cntEdge;
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
    cout.tie(NULL);

    int v, e; cin >> v >> e;
    cntEdge.resize(v + 1, 0);
    roots.reserve(v + 1);
    for (int i = 0; i <= v; i++)
        roots.push_back(i);
    int a, b, rootA, rootB;
    for (int i = 0; i < e; i++) {
        cin >> a >> b;
        cntEdge[a]++;
        cntEdge[b]++;

        rootA = find(a);
        rootB = find(b);
        if (rootA != rootB)
            merge(rootA, rootB);
    }
    
    int cntOdd = 0;
    for (int i = 1; i <= v; i++)
        if (cntEdge[i] & 1)
            cntOdd++;
    
    int rootStd = find(1);
    bool flag = true;
    for (int i = 2; i <= v; i++) {
        if (find(i) != rootStd) {
            flag = false;
            break;
        }
    }
    
    cout << (flag && cntOdd <= 2 ? "YES" : "NO");
}