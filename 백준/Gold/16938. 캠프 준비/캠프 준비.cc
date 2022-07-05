#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> vLevel;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, l, r, x; cin >> n >> l >> r >> x;
    vLevel.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> vLevel[i];
    }
    sort(vLevel.begin(), vLevel.end(), less<int>());
    int ans = 0;
    for (int s = 0; s < n-1; s++) {
        for (int e = n-1; e >= 0; e--) {
            if (vLevel[e] - vLevel[s] < x) {
                break;
            }
            int lb = 1 << s | 1 << e;
            int ub = ((1 << (e+1)) - 1) ^ ((1 << s) - 1);
            for (int bit = lb; bit <= ub; bit += 1 << (s+1)) {
                int sumLev = 0;
                for (int i = s; i <= e; i++) {
                    if (bit & 1 << i) {
                        sumLev += vLevel[i];
                    }
                }
                if (l <= sumLev && sumLev <= r) {
                    ans++;
                }
            }
        }
    }
    cout << ans;
}