#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

const int ROOT = 1;
vector<vector<int>> graph;
vector<ll> dp;

int N;

void makeDP(int curNode, int parentNode) {
    for (int &childNode : graph[curNode]) {
        if (childNode != parentNode) {
            makeDP(childNode, curNode);
            dp[curNode] += dp[childNode];
        }
    }
}

ll getAns(int curNode, int parentNode, int depth) {
    ll ret = dp[curNode] * (N - dp[curNode]);

    ret += (dp[curNode] * (dp[curNode] - 1) / 2) * depth;

    for (int &childNode : graph[curNode]) {
        if (childNode != parentNode) {
            ret -= (dp[childNode] * (dp[childNode] - 1) / 2) * depth;
            ret += getAns(childNode, curNode, depth + 1);
        }
    }
    return ret;
}


int main (){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    cin >> N;
    graph.resize(N + 1, vector<int>());
    dp.resize(N + 1, 1);
    for (int i = 1; i < N; i++) {
        int a, b; cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    makeDP(ROOT, 0);
    cout << getAns(ROOT, 0, 0);
    return 0;
}