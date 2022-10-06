#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const int MAX_L = 1e5;
ll maxY[MAX_L];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    ll w, h, L; cin >> w >> h >> L;
    ll ans = 0;

    for (ll x = 1; x < L; x++) {
        ll y = sqrt(L * L - x * x);
        ans += 3 * y;
    }
    ans += 2 * L;

    if (L > h) {
        ll rad = L - h;
        for (ll x = 0; x <= rad; x++) {
            ll y = sqrt(rad * rad - x * x);
            maxY[x] = y + h;
        }
    }
    if (L > w) {
        ll rad = L - w;
        for (ll x = 0; x <= rad; x++) {
            ll y = sqrt(rad * rad - x * x);
            maxY[x+w] = max(maxY[x+w], y);
        }
        ans += rad;
    }

    for (ll x = 0; x <= w; x++) {
        if (maxY[x] > h) {
            ans += maxY[x] - h;
        }
    }
    for (ll x = w + 1; x <= L; x++) {
        ans += maxY[x];
    }
    cout << ans;
    return 0;
}