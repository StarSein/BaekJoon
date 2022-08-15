#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;


vector<int> digraph;
vector<bool> visit;
int ans = 0;

void dfs(int node) {
    if (visit[node]) {
        ans = -1;
    } else if (digraph[node]) {
        ans++;
        visit[node] = true;
        dfs(digraph[node]);
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M, P; cin >> N >> M >> P;
    digraph.resize(M + 1, 0);
    visit.resize(M + 1, false);
    for (int i = 0; i < N; i++) {
        int a, b; cin >> a >> b;
        if (digraph[b] == 0) {
            digraph[b] = a;
        }
    }
    dfs(P);
    cout << ans;
    return 0;
}