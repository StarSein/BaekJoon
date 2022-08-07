#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

const ll MOD = 1e9+7;

vector<vector<ll>> operator * (vector<vector<ll>> &a, vector<vector<ll>> &b) {
    vector<vector<ll>> ret(2, vector<ll>(2));
    for (int r = 0; r < 2; r++) {
        for (int c = 0; c < 2; c++) {
            ret[r][c] = (a[r][0] * b[0][c] + a[r][1] * b[1][c]) % MOD;
        }
    }
    return ret;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    ll n; cin >> n;
    vector<vector<ll>> res({{1, 1}, {1, 0}});
    n--;
    while (n) {
        ll i = 1;
        vector<vector<ll>> matrix({{1, 1}, {1, 0}});
        while ((i << 1) <= n) {
            matrix = matrix * matrix;
            i <<= 1;
        }
        res = res * matrix;
        n -= i;
    }
    cout << res[0][1];
    return 0;
}