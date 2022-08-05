#include <iostream>
#include <vector>
#include <tuple>
using namespace std;
typedef tuple<int, int, int> ti;
typedef long long ll;

int N, K, D;
vector<ti> vRule;

bool pSearch(int endBox) {
    ll numAcorn = 0;
    for (auto &[A, B, C] : vRule) {
        if (endBox >= A) {
            numAcorn += (min(B, endBox) - A) / C + 1;
        }
    }
    return numAcorn >= D;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> K >> D;
    vRule.reserve(K);
    for (int i = 0; i < K; i++) {
        int A, B, C; cin >> A >> B >> C;
        vRule.emplace_back(A, B, C);
    }

    int ans = 0, left = 1, right = N;
    while (left <= right) {
        int mid = (left + right) >> 1;
        if (pSearch(mid)) {
            ans = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    cout << ans;
    return 0;
}