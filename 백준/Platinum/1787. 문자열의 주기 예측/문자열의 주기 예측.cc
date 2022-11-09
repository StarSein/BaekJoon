#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    string s; cin >> s;

    vector<int> fails(n);
    int j = 0;
    for (int i = 1; i < n; i++) {
        while (j && s[i] != s[j]) {
            j = fails[j - 1];
        }
        if (s[i] == s[j]) {
            fails[i] = ++j;
        }
    }
    ll ans = 0;
    vector<int> cnts(n, 1);
    for (int i = n - 1; i >= 0; i--) {
        if (fails[i]) {
            ans += i + 1;
            if (fails[fails[i] - 1]) {
                cnts[fails[i] - 1] += cnts[i];
            } else {
                ans -= fails[i] * cnts[i];
            }
        }
    }
    cout << ans;
    return 0;
}
