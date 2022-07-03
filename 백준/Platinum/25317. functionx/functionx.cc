#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef tuple<ll, ll, ll, ll> tll;

vector<tll> vQuery;
vector<ll> vNum;
set<ll> setX;
map<ll, int> mIdx;
vector<bool> seg;

ll gcd(ll a, ll b) {
    ll tmp;
    while (b) {
        tmp = a;
        a = b;
        b = tmp % b;
    }
    return a;
}

inline ll sgn(ll x) {
    if (x > 0)      return 1;
    else if (x < 0) return -1;
    else            return 0;
}

void update(int node, int s, int e, int x) {
    if (s > x || e < x) {
        return;
    }
    if (s == x && e == x) {
        seg[node] = !seg[node];
        return;
    }
    int mid = (s + e) >> 1;
    update(node<<1, s, mid, x);
    update(node<<1|1, mid+1, e, x);
    seg[node] = seg[node<<1] ^ seg[node<<1|1];
}

bool query(int node, int s, int e, int l, int r) {
    if (s > r || e < l) {
        return false;
    }
    if (l <= s && e <= r) {
        return seg[node];
    }
    int mid = (s + e) >> 1;
    return query(node<<1, s, mid, l, r) ^ query(node<<1|1, mid+1, e, l, r);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int q; cin >> q;
    ll type, a, b, c, key;
    for (int i = 0; i < q; i++) {
        cin >> type;
        if (type == 1) {
            cin >> a >> b;
            if (a) {
                if (gcd(abs(a), abs(b)) == abs(a)) {
                    key = b/a;
                } else {
                    key = floor((long double)b/a);
                }
                vQuery.emplace_back(type, key, a, b);
                vNum.emplace_back(key);
            } else {
                vQuery.emplace_back(type, 0, a, b);
            }
        } else {
            cin >> c;
            key = -c;
            vQuery.emplace_back(type, key, 0, 0);
            vNum.emplace_back(key);
        }
    }
    sort(vNum.begin(), vNum.end());
    vNum.erase(unique(vNum.begin(), vNum.end()), vNum.end());
    for (int i = 0; i < vNum.size(); i++) {
        mIdx.emplace(vNum[i], i+1);
    }

    ll k = 1;
    ll n = vNum.size(), res;
    seg.resize(4*(n+1));
    for (int i = 0; i < vQuery.size(); i++) {
        tie(type, key, a, b) = vQuery[i];
        if (type == 1) {
            if (a) {
                if (gcd(abs(a), abs(b)) == abs(a)) {
                    setX.insert(key);
                }
                k *= sgn(a);
                update(1, 1, n, mIdx[key]);
            } else {
                k *= sgn(b);
            }
        } else {
            if (setX.find(key) != setX.end()) {
                res = 0;
            } else {
                res = k * (query(1, 1, n, 1, mIdx[key]-1) ? -1 : 1);
            }
            if (res == 1)      cout << "+\n";
            else if (res == -1) cout << "-\n";
            else              cout << "0\n";
        }
    }
}