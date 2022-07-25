#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

const int SZ = 1e6+1;
set<pi> sSeg;
int maxR[SZ], minL[SZ];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    fill(maxR, maxR + SZ, -1);
    fill(minL, minL + SZ, SZ + 1);

    int N, Q;
    cin >> N;
    
    for (int i = 0; i < N; i++) {
        int l, r; cin >> l >> r;
        sSeg.emplace(l, r);
        maxR[l] = max(maxR[l], r);
        minL[r] = min(minL[r], l);
    }
    cin >> Q;
    for (int i = 0; i < Q; i++) {
        int l, r; cin >> l >> r;
        if (sSeg.count(make_pair(l, r))) {
            cout << "1\n";
        } else if (maxR[l] > r && minL[r] < l) {
            cout << "2\n";
        } else {
            cout << "-1\n";
        }
    }
}