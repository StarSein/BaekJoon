#include <iostream>
#include <vector>
#include <queue>
using namespace std;
typedef pair<int, int> pi;


int n, m, s, e;
vector<vector<pi>> graph;

bool isAble(int numGold) {
    vector<bool> visited(n + 1, false);
    queue<int> q;
    q.push(s);
    visited[s] = true;
    int curNode;
    while (!q.empty()) {
        curNode = q.front();
        if (curNode == e)   
            return true;
        q.pop();
        for (pi& e : graph[curNode]) {
            if (!visited[e.first] && e.second >= numGold) {
                q.push(e.first);
                visited[e.first] = true;
            }
        }
    }
    return false;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    cin >> n >> m >> s >> e;
    graph.resize(n + 1, vector<pi> ());
    int h1, h2, k;
    int maxK = 0;
    for (int i = 0; i < m; i++) {
        cin >> h1 >> h2 >> k;
        graph[h1].push_back({h2, k});
        graph[h2].push_back({h1, k});
        if (k > maxK)   
            maxK = k; 
    }

    int mid, left = 1, right = maxK;
    int answer = 0;
    while (left <= right) {
        mid = left + (right - left) / 2;
        if (isAble(mid)) {
            answer = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    cout << answer;
}