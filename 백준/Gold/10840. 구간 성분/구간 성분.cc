#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;
typedef long long ll;

const int P = 1511, MAX_N = 1'501, NUM_C = 26;
const int MOD[2] {(int)1e9+7, (int)1e9+9};
ll h[MAX_N][2], p_pow[NUM_C][2];
vector<ll> vHash;

void before_hash(string &s) {
    memset(h, 0, sizeof(h));
    memset(p_pow, 0, sizeof(p_pow));

    for (int i = 0; i < 2; i++) {
        p_pow[0][i] = 1;
        for (int j = 1; j < NUM_C; j++) {
            p_pow[j][i] = (p_pow[j-1][i] * P) % MOD[i];
        }
    }
    for (int i = 0; i < 2; i++) {
        for (int j = 1; j <= s.size(); j++) {
            int idx = s[j-1]-'a';
            h[j][i] = (h[j-1][i] + p_pow[idx][i]) % MOD[i];
        }
    }
}

ll compute_hash(string &s, int l, int r) {
    ll ret[2] {0, 0};
    for (int i = 0; i < 2; i++) {
        ret[i] = (h[r][i] - h[l][i] + MOD[i]) % MOD[i];
    }
    return ret[0] * MOD[1] + ret[1];
}

bool binary_search(ll target) {
    int left = 0, right = vHash.size() - 1;
    while (left <= right) {
        int mid = (left + right) >> 1;
        if (vHash[mid] > target) {
            right = mid - 1;
        } else if (vHash[mid] < target) {
            left = mid + 1;
        } else {
            return true;
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string a, b; cin >> a >> b;

    before_hash(a);
    vHash.reserve(a.size() * (a.size() + 1) / 2);
    for (int k = a.size(); k > 0; k--) {
        for (int i = 0; i <= a.size() - k; i++) {
            vHash.push_back(compute_hash(a, i, i+k));
        }
    }
    sort(vHash.begin(), vHash.end());

    before_hash(b);
    for (int k = b.size(); k > 0; k--) {
        for (int i = 0; i <= b.size() - k; i++) {
            if (binary_search(compute_hash(b, i, i+k))) {
                cout << k;
                return 0;
            }
        }
    }
    cout << 0;
}