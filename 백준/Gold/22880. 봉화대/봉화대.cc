#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const int MAX_N = 5e5, MOD = 1e9+7;
int h[MAX_N];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> h[i];
    }
    int maxH = h[0], prevI = 0;
    ll ans = 1;
    for (int i = 1; i < N; i++) {
        if (h[i] > maxH) {
            ans = (ans * (i - prevI + 1)) % MOD;
            maxH = h[i];
            prevI = i;
        }
    }
    cout << ans;
}