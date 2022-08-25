#include <bits/stdc++.h>
using namespace std;

vector<int> root;

int find(int a) {
    if (root[a] == a) {
        return a;
    }
    return root[a] = find(root[a]);
}

void merge(int a, int b) {
    if (a > b) {
        swap(a, b);
    }
    root[b] = a;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    root.resize(n);
    for (int i = 0; i < n; i++) {
        root[i] = i;
    }
    for (int i = 1; i <= m; i++) {
        int a, b; cin >> a >> b;

        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            cout << i;
            return 0;
        } else {
            merge(ra, rb);
        }
    } 
    cout << 0;
    return 0;
}