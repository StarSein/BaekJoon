#include <iostream>
#include <unordered_set>
using namespace std;

const int MAX_N = 1e5 + 1;
const int MAX_D = 1001;
int N, K;
int parent[MAX_N];
int color[MAX_N];
int check[MAX_N];
int tmp = 1;

int LCA(int a, int b) {
    for (int i = 0; a >= 0 && i < MAX_D; i++, a = parent[a]) {
        check[a] = tmp;
    }
    for (int i = 0; i < MAX_D; i++, b = parent[b]) {
        if (check[b] == tmp) {
            return b;
        }
    }
    return -1;
}

void Paint(int a, int b, int c) {
    int lca = LCA(a, b);
    for (; a != lca; a = parent[a]) {
        color[a] = c;
    }
    for (; b != lca; b = parent[b]) {
        color[b] = c;
    }
}

void Move(int a, int b) {
    parent[a] = b;
}

int Count(int a, int b) {
    unordered_set<int> s;
    int lca = LCA(a, b);
    for (; a != lca; a = parent[a]) {
        s.insert(color[a]);
    }
    for (; b != lca; b = parent[b]) {
        s.insert(color[b]);
    }
    return (int) s.size();
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> K;
    parent[0] = -1;

    int r, a, b, c;
    for (; tmp <= K; tmp++) {
        cin >> r;
        if (r == 1) {
            cin >> a >> b >> c;
            Paint(a, b, c);
        } else if (r == 2) {
            cin >> a >> b;
            Move(a, b);
        } else {
            cin >> a >> b;
            cout << Count(a, b) << '\n';
        }
    }
}