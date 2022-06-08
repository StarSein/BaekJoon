#include <iostream>
#include <vector>
#include <numeric>
#include <queue>
using namespace std;

const int MAX_N = 15;
vector<bool> vCheck(MAX_N, false);
vector<vector<int>> vEdgeIn(MAX_N, vector<int>()), 
                    vEdgeOut(MAX_N, vector<int>());

int bfs(int startNode) {
    queue<int> q;
    q.push(startNode);
    int cnt = 1;
    while (!q.empty()) {
        int curNode = q.front();
        q.pop();
        for (int nextNode : vEdgeOut[curNode])
            q.push(nextNode);
        cnt++;
    }
    return cnt;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int tc = 0;
    int u, v;
    while (++tc) {
        for (int i = 1; i < MAX_N; i++) {
            vCheck[i] = false;
            vEdgeIn[i].clear();
            vEdgeOut[i].clear();
        }
        cin >> u >> v;
        if (u == -1 && v == -1)
            break;
        int cntEdge = 0;
        while (u != 0 && v != 0) {
            cntEdge++;
            vCheck[u] = true;
            vCheck[v] = true;
            vEdgeIn[v].push_back(u);
            vEdgeOut[u].push_back(v);
            cin >> u >> v;
        }
        bool isTree = true;
        int root, cntRoot = 0;
        for (int i = 1; i < MAX_N; i++) {
            if (vCheck[i]) {
                if (vEdgeIn[i].size() == 0) {
                    cntRoot++;
                    root = i;
                }
                else if (vEdgeIn[i].size() > 1) {
                    isTree = false;
                    break;
                }
            }
        }
        if (isTree && cntEdge) {
            if (cntRoot != 1)
                isTree = false;
            else if (bfs(root) < accumulate(vCheck.begin(), vCheck.end(), 0))
                isTree = false;
        }
        cout << "Case " << tc << " is " << (isTree ? "a tree." : "not a tree.") << '\n';
    }
}