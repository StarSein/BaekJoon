#include <iostream>
#include <queue>
#include <vector>
#include <tuple>
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;

const int MAX_N = 1e5+1;
ll maxScore[MAX_N];
bool check[MAX_N];
vector<vector<pll>> vvTip;
priority_queue<pll, vector<pll>, greater<pll>> pq;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    vvTip.resize(n+1, vector<pll>());
    for (int i = 1; i <= n; i++) {
        cin >> maxScore[i];
    }
    int q; cin >> q;
    int a, b, t;
    for (int i = 0; i < q; i++) {
        cin >> a >> b >> t;
        maxScore[b] += t;
        vvTip[a].emplace_back(b, t);
    }
    for (int i = 1; i <= n; i++) {
        pq.emplace(maxScore[i], i);
    }
    ll answer = 0;
    ll score, mob;
    while (!pq.empty()) {
        tie(score, mob) = pq.top();
        pq.pop();
        if (check[mob]) {
            continue;
        }
        if (m-- == 0) {
            break;
        }
        check[mob] = true;
        answer = max(answer, score);
        for (auto& [target, change] : vvTip[mob]) {
            if (check[target]) {
                continue;
            }
            maxScore[target] -= change;
            pq.emplace(maxScore[target], target);
            
        }
    }
    cout << answer;
}