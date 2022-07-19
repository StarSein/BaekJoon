#include <iostream>
#include <queue>
#include <vector>
using namespace std;

queue<int> q;
vector<int> ans;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k; cin >> n >> k;
    for (int i = 1; i <= n; i++) {
        q.push(i);
    }

    ans.reserve(n);
    while (!q.empty()) {
        for (int i = 1; i < k; i++) {
            q.push(q.front());
            q.pop();
        }
        ans.push_back(q.front());
        q.pop();
    }
    cout << '<' << ans[0];
    for (int i = 1; i < n; i++) {
        cout << ", " << ans[i];
    }
    cout << '>';
}