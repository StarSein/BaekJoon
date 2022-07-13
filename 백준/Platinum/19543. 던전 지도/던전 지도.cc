#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

vector<string> vBlock;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, k; cin >> n >> m >> k;
    vBlock.resize(k);
    for (int i = 0; i < k; i++) {
        cin >> vBlock[i];
    }
    string str; cin >> str;

    int lp = m - 1, rp = m - 1;
    ll ans = 0;
    string &block = vBlock[str[str.size()-1]-'A'];
    while (lp && block[lp-1] == 'R') {
        lp--;
    }
    ans += (rp - lp + 1);
    if (str.size() >= 2) {
        for (int i = str.size() - 2; i >= 0; i--) {
            string &block = vBlock[str[i]-'A'];
            while (lp && block[lp-1] == 'R') {
                lp--;
            }
            while (rp >= 0 && block[rp] != 'U') {
                rp--;
            }
            ans += (rp - lp + 1);
        }
    }
    cout << ans;
}