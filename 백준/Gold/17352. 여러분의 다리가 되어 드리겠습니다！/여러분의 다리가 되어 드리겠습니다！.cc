#include <iostream>
#include <vector>
using namespace std;


vector<int> roots;
vector<int> answer;

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

    int n; cin >> n;
    roots.reserve(n + 1);
    for (int i = 0; i <= n; i++)
        roots.push_back(i);
    
    int u, v, rootU, rootV;
    for (int i = 2; i < n; i++) {
        cin >> u >> v;
        rootU = find(u);
        rootV = find(v);
        if (rootU != rootV)
            merge(rootU, rootV);
    }

    int curRoot, prevRoot = 0;
    for (int i = 1; i <= n; i++) {
        curRoot = find(i);
        if (curRoot != prevRoot) {
            answer.push_back(i);
            prevRoot = curRoot;
        }
    }
    cout << answer[0] << ' ' << answer[1];
}