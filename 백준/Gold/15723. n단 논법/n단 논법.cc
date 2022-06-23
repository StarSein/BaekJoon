#include <iostream>
#include <vector>
#include <queue>
using namespace std;

const int NUM_CHAR = 26;
vector<vector<int>> graph(NUM_CHAR);

bool dfs(int s, int e) {
    queue<int> q;
    q.push(s);
    int bit = 1 << s;
    while (!q.empty()) {
        int curNode = q.front();
        if (curNode == e) {
            return true;
        }
        q.pop();
        for (int& nextNode : graph[curNode]) {
            if (~bit & 1 << nextNode) {
                q.push(nextNode);
                bit |= 1 << nextNode;
            }
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    char p, q; string s;
    for (int i = 0; i < n; i++) {
        cin >> p >> s >> q;
        graph[p-'a'].push_back(q-'a');
    }
    int m; cin >> m;
    for (int i = 0; i < m; i++) {
        cin >> p >> s >> q;
        cout << (dfs(p-'a', q-'a') ? 'T' : 'F') << '\n';
    }
}