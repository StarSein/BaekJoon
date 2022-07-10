#include <iostream>
#include <vector>
using namespace std;
typedef pair<int, int> pi;

const int MAX_C =1'001, INF = 1e5;
int dp[MAX_C];
vector<pi> vec;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int c, n; cin >> c >> n;
    vec.reserve(n);
    for (int i = 0; i < n; i++) {
        int cost, gain; cin >> cost >> gain;
        vec.emplace_back(cost, gain);
    }

    fill(dp, dp + MAX_C, INF);
    dp[0] = 0;
    for (int i = 1; i <= c; i++) {
        for (auto &[cost, gain] : vec) {
            dp[i] = min(dp[i], dp[max(i-gain, 0)] + cost);
        }
    }
    cout << dp[c];
}