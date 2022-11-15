#include <iostream>
#include <numeric>
#include <algorithm>
using namespace std;
typedef long long ll;

const int MAX_N = 1e6;
ll n, d;
ll a[MAX_N];
ll b[MAX_N];
ll ans[2];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> d;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    for (int k = 0; k < 2; k++) {
        for (int i = 0; i < n; i++) {
            b[i] = a[i] - (i - 1) * d;
        }
        ll maxVal = *max_element(b, b + n);
        ll minVal = *min_element(b, b + n);

        ans[k] = maxVal - minVal;
        reverse(a, a + n);
    }
    ll answer = min(ans[0], ans[1]);

    cout << answer / 2;
    if (answer & 1) {
        cout << ".5";
    } else {
        cout << ".0";
    }
    return 0;
}