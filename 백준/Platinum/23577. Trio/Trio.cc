#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const int MAX_N = 2000;
bitset<MAX_N> sets[10][4];
string arr[MAX_N];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    if (n < 3) {
        cout << 0;
        return 0;
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 4; j++) {
            sets[arr[i][j] - '0'][j].set(i);
            sets[0][j].set(i);
        }
    }
    ll ans = 0;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            bitset<MAX_N> bs;
            bs.set();
            for (int pos = 0; pos < 4; pos++) {
                if (arr[i][pos] == arr[j][pos]) {
                    bs &= sets[arr[i][pos] - '0'][pos];
                } else {
                    int idx1 = arr[i][pos] - '0';
                    int idx2 = arr[j][pos] - '0';
                    bs &= (sets[0][pos] ^ (sets[idx1][pos] | sets[idx2][pos]));
                }
            }
            ans += bs.count();
        }
    }
    cout << ans / 3;
    return 0;
}