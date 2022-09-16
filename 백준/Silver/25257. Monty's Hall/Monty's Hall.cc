#include <bits/stdc++.h>
using namespace std;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int d, s, e; cin >> d >> s >> e;
    double ans = 0.0;
    for (int i = 0; i <= min(d - (s + e), s); i++) {
        ans = max(ans, ((double)s - i) / d + (((double)d - s) / d) * ((double)i / (d - (s + e))));
    }
    cout << ans;
    return 0;
}