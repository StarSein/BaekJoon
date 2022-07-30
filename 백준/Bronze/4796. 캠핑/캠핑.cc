#include <iostream>
using namespace std;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int T = 0;
    while (++T) {
        int L, P, V; cin >> L >> P >> V;
        if (L == 0) {
            break;
        }
        int ans = L * (V / P) + min(L, V % P);
        cout << "Case " << T << ": " << ans << '\n';
    }
    return 0;
}