#include <iostream>
#include <queue>
#include <vector>
#include <tuple>
using namespace std;
typedef pair<int, int> pi;

typedef struct edge {
    int weight, nextNode;
} edge;

const int MAX_N = 1e5;
int n, m, a, b, c;
vector<vector<edge>> graph(MAX_N + 1, vector<edge>());

bool is_able(int weightLimit) {
    priority_queue<pi, vector<pi>, greater<pi>> pq;
    pq.push({0, a});
    int curCost, curNode;
    vector<bool> visit(n + 1, false);
    while (!pq.empty()) {
        tie(curCost, curNode) = pq.top();
        pq.pop();
        if (visit[curNode])
            continue;
        if (curNode == b && curCost <= c)
            return true;
        visit[curNode] = true;
        for (auto& e : graph[curNode]) {
            if (e.weight <= weightLimit) {
                pq.push({curCost + e.weight, e.nextNode});
            }
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> n >> m >> a >> b >> c;
    int u, v, w, maxW = 0;
    for (int i = 0; i < m; i++) {
        cin >> u >> v >> w;
        graph[u].push_back({w, v});
        graph[v].push_back({w, u});
        maxW = max(maxW, w);
    }
    int mid, left = 0, right = maxW;
    int answer = -1;
    while (left <= right) {
        mid = left + (right - left) / 2;
        if (is_able(mid)) {
            answer = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    } 
    cout << answer;
}