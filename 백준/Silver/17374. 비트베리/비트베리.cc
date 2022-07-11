#include <bits/stdc++.h>
using namespace std;


int a, b, c, d;

bool pSearch(int num, int p, int coin) {
    if (p > num) {
        coin += ((p - num) / a) * b;
    }
    if (coin > num) {
        p += ((coin - num) / b) * a;
    }
    return (p >= num && coin >= num);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t; cin >> t;
    while (t--) {
        int p, q; cin >> p >> q >> a >> b >> c >> d;
        int coin = (q / c) * d;

        int ans, left = 0, right = 1e9;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (pSearch(mid, p, coin)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        cout << ans << '\n';
    }
}