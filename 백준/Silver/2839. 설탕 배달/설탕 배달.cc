#include <iostream>
using namespace std;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    int ans = -1;
    for (int fiveCnt = 1000; fiveCnt >= 0; fiveCnt--) {
        int rest = N - 5 * fiveCnt;
        if (rest >= 0 && rest % 3 == 0) {
            ans = fiveCnt + rest / 3;
            break;
        }
    }
    cout << ans;
    return 0;
}