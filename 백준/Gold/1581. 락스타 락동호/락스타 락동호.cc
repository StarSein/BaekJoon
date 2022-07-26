#include <iostream>
using namespace std;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int FF, FS, SF, SS; cin >> FF >> FS >> SF >> SS;
    int ans = 0;
    ans += FF;
    if (!FF || FS) {
        ans += SS;
        ans += 2 * min(FS, SF);
        if (FS > SF) {
            ans++;
        }
        if (!FS && SF) {
            ans++;
        }
    }
    cout << ans;
}