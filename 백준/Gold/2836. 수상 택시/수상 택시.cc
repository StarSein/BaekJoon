#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
typedef long long ll;
typedef pair<int, int> pi;

vector<pi> vec;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M; cin >> N >> M;
    vec.reserve(N);
    for (int i = 0; i < N; i++) {
        int s, e; cin >> s >> e;
        if (s > e) {
            vec.emplace_back(e, s);
        }
    }

    sort(vec.begin(), vec.end(), [](pi &a, pi &b) {return a.first < b.first;});

    ll ans = M;
    int prevL = -1;
    int prevR = -1;
    for (auto &[l, r] : vec) {
        if (l > prevR) {
            ans += 2 * (prevR - prevL);
            prevL = l;
            prevR = r;
        } else if (r <= prevR) {
            continue;
        } else {
            prevR = r;
        }
    }
    ans += 2 * (prevR - prevL);
    cout << ans;
    return 0;
}