#include <iostream>
#include <set>
using namespace std;
#define P 31;

const int MAX_KEY = 1'000;
multiset<int> rel[MAX_KEY];
int ans = 0;

int compute_hash(string &s) {
    return (s[0] - 'A') + (s[1] - 'A') * P;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        string s, code; cin >> s >> code;
        int sh = compute_hash(s);
        int ch = compute_hash(code);
        if (sh == ch) {
            continue;
        }
        ans += rel[ch].count(sh);
        rel[sh].insert(ch);
    }
    cout << ans;
}