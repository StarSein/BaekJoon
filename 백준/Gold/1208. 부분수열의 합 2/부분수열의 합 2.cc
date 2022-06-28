#include <iostream>
using namespace std;
typedef long long ll;

const int SIZE = 8e6+1, MID = 4e6;
ll dp[SIZE];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, s; cin >> n >> s;
    for (int i = 0; i < n; i++) {
        int num; cin >> num;

        if (num >= 0) {
            for (int j = SIZE-num-1; ~j; j--) {
                dp[j+num] += dp[j];
            }
        } else {
            for (int j = -num; j < SIZE; j++) {
                dp[j+num] += dp[j];
            }
        }
        dp[num+MID]++;
    }
    cout << dp[s+MID];
}