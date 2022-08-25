#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

vector<pi> cops;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    cops.reserve(N);
    for (int i = 0; i < N; i++) {
        int xi, yi; cin >> xi >> yi;

        cops.emplace_back(xi, yi);
    }
    int sx, sy; cin >> sx >> sy;

    bool isNorth = true, isEast = true, isSouth = true, isWest = true;
    for (auto &[x, y] : cops) {
        int absDiffX = abs(x - sx);
        int absDiffY = abs(y - sy);

        bool flagX = false, flagY = false;

        if (absDiffX == absDiffY) {
            flagX = true;
            flagY = true;
        } else if (absDiffX > absDiffY) {
            flagX = true;
        } else {
            flagY = true;
        }

        if (flagX) {
            if (x > sx) {
                isEast = false;
            } else {
                isWest = false;
            }
        }
        if (flagY) {
            if (y > sy) {
                isNorth = false;
            } else {
                isSouth = false;
            }
        }
    }

    cout << ((isNorth || isEast || isSouth || isWest) ? "YES" : "NO");
    return 0;
}