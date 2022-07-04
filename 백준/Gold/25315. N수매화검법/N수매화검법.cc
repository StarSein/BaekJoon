#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second
typedef long long ll;
typedef pair<ll, ll> pll;
typedef tuple<int, ll, pll, pll> tll;

vector<tll> vSeg;
vector<vector<int>> vIntersect;
vector<bool> cut;

int getSA(pll& p1, pll& p2, pll& p3) {
    ll sa = (p1.X * p2.Y + p2.X * p3.Y + p3.X * p1.Y) - (p2.X * p1.Y + p3.X * p2.Y + p1.X * p3.Y);
    if (sa > 0)       return 1;
    else if (sa == 0) return 0;
    else              return -1;
}

bool isIntersect(int& a, int& b) {
    auto &[ia, wa, sa, ea] = vSeg[a];
    auto &[ib, wb, sb, eb] = vSeg[b];

    return (getSA(sa, ea, sb) * getSA(sa, ea, eb) < 0 && \
            getSA(sb, eb, sa) * getSA(sb, eb, ea) < 0);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    ll sx, sy, ex, ey, w;
    vSeg.reserve(n);
    vIntersect.resize(n, vector<int>());
    for (int i = 0; i < n; i++) {
        cin >> sx >> sy >> ex >> ey >> w;
        vSeg.emplace_back(i, w, pair(sx, sy), pair(ex, ey));
    }
    for (int i = 0 ; i < n-1; i++) {
        for (int j = i+1; j < n; j++) {
            if (isIntersect(i, j)) {
                vIntersect[i].push_back(j);
                vIntersect[j].push_back(i);
            }
        }
    }
    sort(vSeg.begin(), vSeg.end(), [](tll& a, tll& b)->bool{return get<1>(a) < get<1>(b);});
    
    cut.resize(n, false);
    ll cnt, answer = 0;
    for (auto &[i, w, s, e] : vSeg) {
        cnt = 1;
        for (int& j : vIntersect[i]) {
            if (!cut[j]) {
                cnt++;
            }
        }
        answer += cnt * w;
        cut[i] = true;
    }
    cout << answer;
}