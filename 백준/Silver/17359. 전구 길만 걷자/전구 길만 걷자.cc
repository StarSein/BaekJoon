#include <iostream>
using namespace std;

const int BIN = 2;
int cnt[BIN][BIN];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    
    int ans = 0;
    for (int i = 0; i < N; i++) {
        string s; cin >> s;
        cnt[s.front() - '0'][s.back() - '0']++;

        for (int j = 1; j < s.size(); j++) {
            if (s[j] != s[j - 1]) {
                ans++;
            }
        }
    }

    if (N > 1) {
        if (cnt[0][1] == 0 && cnt[1][0] == 0) {
            if (cnt[0][0] != 0 && cnt[1][1] != 0) {
                ans++;
            }
        } else if (cnt[1][0] == 0) {
            ans += cnt[0][1] - 1;
        } else if (cnt[0][1] == 0) {
            ans += cnt[1][0] - 1;
        } else {
            ans += max(abs(cnt[0][1] - cnt[1][0]) - 1, 0);
        }
    }
    cout << ans;
    return 0;
}