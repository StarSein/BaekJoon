#include <iostream>
using namespace std;
typedef long long ll;

const int MOD = 1e9+7;

ll getNumCase(int x) {
    ll ret = 1;
    ll perm = 1;
    while (x >= 1) {
        perm *= x--;
        perm %= MOD;
        ret += perm;
        ret %= MOD;
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    int cntN = 0, cntC = 0, cntNC = 0, cntCN = 0;
    string word; size_t posN, posC;
    for (int i = 0; i < n; i++) {
        cin >> word;
        posN = word.find('N');
        posC = word.rfind('C');
        if (posN != string::npos && posC != string::npos) {
            if (posN < posC) {
                cntNC++;
            } else {
                cntCN++;
            }
        } else if (posN != string::npos) {
            cntN++;
        } else if (posC != string::npos) {
            cntC++;
        }
    }
    ll ncN = getNumCase(cntN), ncC = getNumCase(cntC);
    ll answer = getNumCase(n) - (((ncC * ncN) % MOD) * (1 + cntCN)) % MOD;
    while (answer < 0) {
        answer += MOD;
    }
    cout << answer;
}