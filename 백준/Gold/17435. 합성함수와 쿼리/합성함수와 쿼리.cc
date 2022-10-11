#include <iostream>
using namespace std;

const int MAX_M = 2e5 + 1, LOG = 20;
int table[MAX_M][LOG];

int f(int n, int x) {
    int exp = LOG - 1;
    int bit = 1 << exp;
    while (bit) {
        if (bit & n) {
            x = table[x][exp];
        }
        exp--;
        bit >>= 1;
    }
    return x;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int m; cin >> m;
    for (int i = 1; i <= m; i++) {
        cin >> table[i][0];
    }

    for (int exp = 1; exp < LOG; exp++) {
        for (int i = 1; i <= m; i++) {
            int x = table[i][exp-1];
            table[i][exp] = table[x][exp-1];
        }
    }

    int Q; cin >> Q;
    for (int i = 0; i < Q; i++) {
        int n, x; cin >> n >> x;
        cout << f(n, x) << '\n';
    }
    return 0;
}