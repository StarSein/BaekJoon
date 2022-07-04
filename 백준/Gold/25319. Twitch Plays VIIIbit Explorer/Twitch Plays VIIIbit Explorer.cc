#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

const int NUM_CHAR = 26, MAX_L = 1e6;
queue<pi> qLoc[NUM_CHAR];
int cnt[NUM_CHAR];

int ans1;
vector<char> ans3;

void moveMe(int dr, int dc) {
    while (dr > 0) {
        ans3.push_back('D');
        dr--;
    }
    while (dr < 0) {
        ans3.push_back('U');
        dr++;
    }
    while (dc > 0) {
        ans3.push_back('R');
        dc--;
    }
    while (dc < 0) {
        ans3.push_back('L');
        dc++;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m, sz; cin >> n >> m >> sz;
    char x;
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            cin >> x;
            qLoc[x-'a'].emplace(r, c);
        }
    }
    string s; cin >> s;
    for (int i = 0; i < s.size(); i++) {
        cnt[s[i]-'a']++;
    }

    int nr, nc, cr = 0, cc = 0;
    int i;
    ans3.reserve(MAX_L);
    while (true) {
        bool flag = false;
        for (int c = 0; c < NUM_CHAR; c++) {
            if (cnt[c] > qLoc[c].size()) {
                flag = true;
                break;
            }
        }
        if (flag) {
            break;
        }
        for (int pos = 0; pos < s.size(); pos++) {
            i = s[pos] - 'a';
            tie(nr, nc) = qLoc[i].front();
            qLoc[i].pop();

            moveMe(nr-cr, nc-cc);
            ans3.push_back('P');
            cr = nr;
            cc = nc;
        }
        ans1++;
    }
    moveMe(n-1-cr, m-1-cc);
    cout << ans1 << '\n' \
         << ans3.size() << '\n';
    copy(ans3.begin(), ans3.end(), ostream_iterator<char>(cout, ""));
}