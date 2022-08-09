#include <iostream>
using namespace std;
typedef pair<int, int> pi;

const int ROOT = 1, MAX_N = 1e5;

pi child[MAX_N + 1];
int cntMove = 0;

void quasiInOrder(int node, bool isTerminal) {
    if (child[node].first != -1 && child[node].second != -1) {
        cntMove += (isTerminal ? 3 : 4);
        quasiInOrder(child[node].first, false);
        quasiInOrder(child[node].second, isTerminal);
    } else if (child[node].first != -1) {
        cntMove += 2;
        quasiInOrder(child[node].first, false);
    } else if (child[node].second != -1) {
        cntMove += (isTerminal ? 1 : 2);
        quasiInOrder(child[node].second, isTerminal);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        int a, b, c; cin >> a >> b >> c;
        child[a] = {b, c};
    }

    quasiInOrder(ROOT, true);
    cout << cntMove;
    return 0;
}