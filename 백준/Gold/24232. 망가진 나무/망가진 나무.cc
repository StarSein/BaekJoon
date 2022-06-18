#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <iterator>
using namespace std;
typedef pair<int, int> pi;

vector<vector<int>> graph;
vector<int> dp, cnt;
map<pi, int> idx;
map<pi, bool> isDIR;
vector<bool> answer;


void dfs1(int current, int parent) {
    for (int& next : graph[current]) {
        if (next != parent) {
            dfs1(next, current);
            dp[current] += dp[next];
        }
    }
    if (isDIR[{current, parent}])
        dp[current]++;
}

void calc(int current, int parent) {
    cnt[current] = cnt[parent] - dp[current];
    for (int& next : graph[current]) {
        if (next != parent) {
            cnt[current] += dp[next];
        }
    }
    if (isDIR[{parent, current}])
        cnt[current]++;
    for (int& next : graph[current]) {
        if (next != parent) {
            calc(next, current);
        }
    }
}

void dfs2(int current, int parent) {
    for (int& next : graph[current]) {
        if (next != parent) {
            if (isDIR[{next, current}]) {
                answer[idx[{next, current}]] = true;
            }
            dfs2(next, current);
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    graph.resize(n + 1, vector<int>());
    dp.resize(n + 1, 0);
    cnt.resize(n + 1, 0);
    int u, v;
    for (int i = 1; i < n; i++) {
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
        isDIR.insert({{u, v}, true});
        isDIR.insert({{v, u}, false});
        idx.insert({{u, v}, i-1});
    }
    isDIR.insert({{1, 0}, false});
    dfs1(1, 0);

    cnt[1] = dp[1];
    for (int& next : graph[1]) {
        calc(next, 1);
    }
    int minCnt = 100'000, node = 0;
    for (int i = 1; i <= n; i++) {
        if (cnt[i] < minCnt) {
            minCnt = cnt[i];
            node = i;
        }
    }
    answer.resize(n-1, false);
    dfs2(node, 0);
    copy(answer.begin(), answer.end(), ostream_iterator<bool>(cout));
}