#include <iostream>
#include <vector>
#include <tuple>
using namespace std;
typedef pair<int, int> pi;

vector<pi> vLoc;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    bool isHere;
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            cin >> isHere;
            if (isHere) vLoc.emplace_back(r, c);
        }
    }
    int ans = 0;
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            int minDist = 51;
            for (auto &[_r, _c] : vLoc) {
                minDist = min(minDist, max(abs(r - _r), abs(c - _c)));
            }
            ans = max(ans, minDist);
        }
    }
    cout << ans;
}