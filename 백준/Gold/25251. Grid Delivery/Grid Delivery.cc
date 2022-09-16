#include <bits/stdc++.h>
using namespace std;

const int MAX_W = 2000;
stack<int> stArr[MAX_W];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int h, w; cin >> h >> w;
    int cnt = 0;
    for (int r = 0; r < h; r++) {
        string s; cin >> s;
        for (int i = 0; i < w; i++) {
            if (s[i] == 'C') {
                stArr[r].push(i);
                cnt++;
            }
        }
    }

    int ans = 0;
    while (cnt) {
        ans++;
        int r = 0, c = 0;
        while (r < h) {
            int nc = -1;
            if (!stArr[r].empty() && c <= stArr[r].top()) {
                nc = stArr[r].top();
            }
            while (!stArr[r].empty() && c <= stArr[r].top()) {
                stArr[r].pop();
                cnt--;
            }
            r++;
            if (nc != -1) {
                c = nc;
            }
        }
    }
    cout << ans;
    return 0;
}