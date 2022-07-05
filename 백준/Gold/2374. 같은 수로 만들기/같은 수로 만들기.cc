#include <iostream>
using namespace std;
typedef long long ll;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    int lastVal;
    cin >> lastVal;
    int num, maxVal = lastVal;
    ll ans = 0;
    for (int i = 1; i < n; i++) {
        cin >> num;
        if (num > lastVal) {
            ans += (num - lastVal);
        }
        lastVal = num;
        maxVal = max(maxVal, num);
    }
    ans += maxVal - lastVal;
    cout << ans;
}