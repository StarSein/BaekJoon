#include <iostream>
#include <cstring>
using namespace std;
typedef long long ll;

const int MAX_N = 1e5;

ll roots[MAX_N + 1], dists[MAX_N + 1];

int getRoot(int x) {
    if (roots[x] == x) {
        return x;
    }
    int ret = getRoot(roots[x]);
    dists[x] += dists[roots[x]];
    return roots[x] = ret;
}

void merge(int a, int b, int w) {
    int ra = getRoot(a);
    int rb = getRoot(b);
    if (ra == rb) {
        return;
    }

    if (ra > rb) {
        swap(ra, rb);
        swap(a, b);
        w *= -1;
    }
    dists[rb] += dists[a] + w - dists[b];
    roots[rb] = ra;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    while (true) {
        int N, M; cin >> N >> M;

        if (N == 0) {
            break;
        }

        for (int i = 1; i <= N; i++) {
            roots[i] = i;
        }
        memset(dists, 0, sizeof(dists));

        int a, b, w;
        for (int i = 0; i < M; i++) {
            char query;
            cin >> query;
            if (query == '!') {
                cin >> a >> b >> w;
                merge(a, b, w);
            } else {
                cin >> a >> b;
                int rootA = getRoot(a);
                int rootB = getRoot(b);
                if (rootA != rootB) {
                    cout << "UNKNOWN\n";
                } else {
                    cout << dists[b] - dists[a] << '\n';
                }
            }
        }
    }
    return 0;
}