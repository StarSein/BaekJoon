#include <iostream>
#include <queue>
#include <vector>
using namespace std;
typedef long long ll;

priority_queue<ll, vector<ll>, greater<ll>> pq;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    int num;
    for (int i = 0; i < n; i++) {
        cin >> num;
        pq.push(num);
    }
    ll x, y;
    for (int i = 0; i < m; i++) {
        x = pq.top(); pq.pop();
        y = pq.top(); pq.pop();
        pq.push(x+y);
        pq.push(x+y);
    }
    ll answer = 0;
    while (!pq.empty()) {
        answer += pq.top();
        pq.pop();
    }
    cout << answer;
}