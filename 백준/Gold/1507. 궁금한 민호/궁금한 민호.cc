#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;
typedef pair<int, int> pi;

int n;
const int INF = 10000;
vector<vector<int>> graph;


int dijkstra(int startNode, int endNode) {
    priority_queue<pi, vector<pi>, greater<pi>> pq;
    pq.push({0, startNode});
    int curCost, curNode;
    int bit = 0;
    while (!pq.empty()) {
        tie(curCost, curNode) = pq.top();
        if (curNode == endNode)
            return curCost;
        pq.pop();
        if (bit & 1 << curNode)
            continue;
        bit |= 1 << curNode;
        for (int i = 0; i < n; i++)
            if (!(bit & 1 << i))
                pq.push({curCost + graph[curNode][i], i});
    }
    return INF;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    cin >> n;
    graph.resize(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> graph[i][j];
    
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            int tmp = graph[i][j];
            graph[i][j] = INF;
            graph[j][i] = INF;
            int minDist = dijkstra(i, j);
            if (minDist < tmp) {
                cout << -1;
                return 0;
            } else if (minDist > tmp) {
                graph[i][j] = tmp;
                graph[j][i] = tmp;
            }
        }
    }
    int answer = 0;
    for (int i = 0; i < n - 1; i++)
        for (int j = i + 1; j < n; j++)
            if (graph[i][j] != INF)
                answer += graph[i][j];
    cout << answer;
}