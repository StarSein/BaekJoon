#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const ll WEIGHT = 100;
const ll SZ = 40 * WEIGHT + 1, MID = 20 * WEIGHT;
bool damaged[SZ][SZ];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        ll x, y, r; cin >> x >> y >> r;
        x *= WEIGHT;
        y *= WEIGHT;
        r *= WEIGHT;

        for (int row = 0; row < SZ; row++) {
            for (int col = 0; col < SZ; col++) {
                ll rowPos = row - MID;
                ll colPos = col - MID;
                if ((rowPos - y) * (rowPos - y) + (colPos - x) * (colPos - x) <= r * r) {
                    damaged[row][col] = true;
                }
            }
        }
    }
    
    ll damagedCnt = 0;
    for (int row = 0; row < SZ; row++) {
        for (int col = 0; col < SZ; col++) {
            if (damaged[row][col])  damagedCnt++;
        }
    }
    cout << static_cast<double>(damagedCnt) / (WEIGHT * WEIGHT);
    return 0;
}