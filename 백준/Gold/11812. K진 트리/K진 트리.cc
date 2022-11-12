#include <iostream>
#include <cmath>
using namespace std;
typedef long long ll;

ll N;
int K;

int depth(ll node) {
    int ret = 0;
    while (node > 1) {
        node = ceil(((double)node - 1) / K);
        ret++;
    }
    return ret;
}

ll dist(ll x, ll y) {
    if (K == 1) {
        return abs(x - y);
    } else {
        int dx = depth(x);
        int dy = depth(y);

        if (dx > dy) {
            swap(x, y);
            swap(dx, dy);
        }
        for (int i = 0; i < dy - dx; i++) {
            y = ceil(((double)y - 1) / K);
        }
        while (x != y) {
            x = ceil(((double)x - 1) / K);
            y = ceil(((double)y - 1) / K);
        }
        int dm = depth(x);
        return (dx + dy - 2 * dm);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int Q;
    cin >> N >> K >> Q;
    for (int i = 0; i < Q; i++) {
        ll x, y; cin >> x >> y;
        cout << dist(x, y) << '\n';
    }
    return 0;
}