#include <iostream>
#include <vector>
#include <numeric>
#include <queue>
using namespace std;

const int MAX_N = 10;

vector<int> populVec;
vector<vector<int>> graph;

bool visit[MAX_N + 1][1 << (MAX_N + 1)];

int populSum, ans;

int getComponentCnt(int mask) {
    int ret = 0;
    for (int i = 1; i < populVec.size(); i++) {
        if (~mask & (1 << i)) {
            ret++;
            queue<int> q;
            mask |= (1 << i);
            q.push(i);
            while (!q.empty()) {
                int curNode = q.front();
                q.pop();

                for (int &nextNode : graph[curNode]) {
                    if (~mask & (1 << nextNode)) {
                        mask |= (1 << nextNode);
                        q.push(nextNode);
                    }
                }
            }
        }
    }
    return ret;
}

void dfs(int mask, int popul) {
    if (getComponentCnt(mask) == 1) {
        ans = min(ans, abs(populSum - 2 * popul));
    }

    for (int i = 1; i < populVec.size(); i++) {
        if (mask & (1 << i)) {
            for (int &adj : graph[i]) {
                int nextMask = mask | (1 << adj);
                if ((~mask & (1 << adj)) && !visit[adj][nextMask]) {
                    visit[adj][nextMask] = true;
                    dfs(nextMask, popul + populVec[adj]);
                }
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    populVec.resize(N + 1);
    for (int i = 1; i <= N; i++) {
        cin >> populVec[i];
    }

    graph.resize(N + 1);
    for (int i = 1; i <= N; i++) {
        int adjNum; cin >> adjNum;
        for (int j = 0; j < adjNum; j++) {
            int adj; cin >> adj;
            graph[i].push_back(adj);
        }
    }

    if (getComponentCnt(0) < 3) {
        populSum = accumulate(populVec.begin(), populVec.end(), 0);
        ans = populSum;

        for (int i = 1; i <= N; i++) {
            visit[i][1 << i] = true;
            dfs(1 << i, populVec[i]);
        }
    } else {
        ans = -1;
    }
    cout << ans;
    return 0;
}