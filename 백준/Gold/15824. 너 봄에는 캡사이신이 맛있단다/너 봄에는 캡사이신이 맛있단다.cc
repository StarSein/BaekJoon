#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;
typedef long long ll;

const ll DIV = 1'000'000'007;
vector<ll> vMenu;
vector<ll> prefSum, suffSum;
vector<ll> vPower;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    vMenu.reserve(n);
    istream_iterator<ll> cin_iter(cin), eos;
    copy(cin_iter, eos, back_inserter(vMenu));

    if (n == 1) {
        cout << 0;
        return 0;
    }
    
    sort(vMenu.begin(), vMenu.end(), less<ll>());
    prefSum.resize(n, 0); prefSum[0] = vMenu[0];
    suffSum.resize(n, 0); suffSum[n-1] = vMenu[n-1];
    for (int i = 1; i < n; i++)
        prefSum[i] = (prefSum[i-1] + vMenu[i]) % DIV;
    for (int i = n-2; i >= 0; i--)
        suffSum[i] = (suffSum[i+1] + vMenu[i]) % DIV;

    vPower.resize(n-1); vPower[0] = 1;
    for (int i = 1; i < n-1; i++) {
        vPower[i] = (2 * vPower[i-1]) % DIV;
    }
    ll answer = 0;
    for (int i = 1; i < n; i++) {
        answer += (suffSum[i] - prefSum[n-1-i]) * vPower[i-1];
        answer %= DIV;
    }
    cout << answer;
}