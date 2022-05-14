#include <iostream>
#include <vector>
using namespace std;

typedef struct task {
    int value, cost;
} task;
vector<task> vTask;
vector<int> dp;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, k; cin >> n >> k;
    int value, cost;
    vTask.reserve(k);
    for (int i = 0; i < k; i++) {
        cin >> value >> cost;
        vTask.push_back({value, cost});
    }

    dp.resize(n + 1, 0);
    for (auto& t : vTask)
        for (int i = n; i >= t.cost; i--)
            dp[i] = max(dp[i], dp[i-t.cost] + t.value);
    
    cout << dp[n];
}