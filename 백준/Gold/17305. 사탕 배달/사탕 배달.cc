#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;

vector<ll> vLight, vHeavy, vPSL, vPSH;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, w; cin >> n >> w;
    vLight.reserve(n);
    vHeavy.reserve(n);

    int t, s;
    for (int i = 0; i < n; i++) {
        cin >> t >> s;
        if (t == 3) {
            vLight.push_back(s);
        } else {
            vHeavy.push_back(s);
        }
    }
    sort(vLight.begin(), vLight.end(), greater<ll>());
    sort(vHeavy.begin(), vHeavy.end(), greater<ll>());

    vPSL.resize(vLight.size() + 1, 0);
    vPSH.resize(vHeavy.size() + 1, 0);

    for (int i = 1; i < vPSL.size(); i++) {
        vPSL[i] = vPSL[i-1] + vLight[i-1];
    }
    for (int i = 1; i < vPSH.size(); i++) {
        vPSH[i] = vPSH[i-1] + vHeavy[i-1];
    }

    ll maxSum = 0, curSum;
    for (int cntL = 0; cntL <= w / 3; cntL++) {
        if (cntL > vLight.size()) {
            break;
        }

        int cntH = (w - 3 * cntL) / 5;
        cntH = min(cntH, (int) vHeavy.size());
        curSum = vPSL[cntL] + vPSH[cntH];
        maxSum = max(maxSum, curSum);
    }
    cout << maxSum;
}