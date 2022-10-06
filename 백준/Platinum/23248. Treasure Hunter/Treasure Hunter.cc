#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> stVec;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int m, n, k; cin >> m >> n >> k;
    stVec.resize(m + 1);
    for (int i = 0; i < k; i++) {
        int r, c; cin >> r >> c;
        stVec[r].push_back(c);
    }
    for (int r = 1; r <= m; r++) {
        sort(stVec[r].begin(), stVec[r].end());
    }

    int ans = 0;
    while (k) {
        ans++;
        int curC = 1;
        for (int r = 1; r <= m; r++) {
            if (stVec[r].empty() || stVec[r].back() < curC) {
                continue;
            }

            int nextC = stVec[r].back();
            while (!stVec[r].empty() && stVec[r].back() >= curC) {
                stVec[r].pop_back();
                k--;
            }
            curC = nextC;
        }
    }
    cout << ans;
    return 0;
}