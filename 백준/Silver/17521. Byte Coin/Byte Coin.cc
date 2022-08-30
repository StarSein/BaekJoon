#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
vector<int> vPrice;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    ll n, W; cin >> n >> W;
    vPrice.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> vPrice[i];
    }
    ll numCoin = 0;
    for (int i = 0; i < n - 1; i++) {
        if (vPrice[i+1] - vPrice[i] > 0) {
            numCoin += W / vPrice[i];
            W %= vPrice[i];
        } else {
            W += numCoin * vPrice[i];
            numCoin = 0;
        }
    }
    W += numCoin * vPrice[n-1];
    cout << W;
}