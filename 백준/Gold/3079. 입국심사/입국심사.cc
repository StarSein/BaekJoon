#include <iostream>
using namespace std;
typedef long long ll;

const int MAX_N = 1e5;
ll T[MAX_N];
int N, M;
ll ans;

bool pSearch(ll time) {
    ll capa = 0;
    for (int i = 0; i < N; i++) {
        capa += (time / T[i]);
        if (capa >= M) {
            return true;
        }
    }
    return false;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> T[i];
    }
    ll left = 1, right = 1e18;
    while (left <= right) {
        ll mid = (left + right) >> 1;
        if (pSearch(mid)) {
            ans = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    cout << ans;
}