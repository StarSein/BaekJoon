#include <iostream>
#include <vector>
using namespace std;


const int INF = 1e6;

void floyd_warshall(int numNode, vector<vector<int>>& graph) {
    for (int k = 1; k <= numNode; k++)
        for (int i = 1; i <= numNode; i++)
            for (int j = 1; j <= numNode; j++)
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int t; cin >> t;
    int n, m, k, a, b, c;
    int minSum, curSum, bestRoom;
    for (int tc = 0; tc < t; tc++) {
        cin >> n >> m;
        vector<vector<int>> graph(n + 1, vector<int> (n + 1, INF));
        for (int i = 0; i < m; i++) {
            cin >> a >> b >> c;
            graph[a][b] = c;
            graph[b][a] = c;
        }
        for (int i = 1; i <= n; i++)
            graph[i][i] = 0;
        cin >> k;
        vector<int> friends(k);
        for (int i = 0; i < k; i++) {
            cin >> friends[i];
        }
        floyd_warshall(n, graph);
        minSum = INF;
        for (int i = 1; i <= n; i++) {
            curSum = 0;
            for (int& j : friends)
                curSum += graph[j][i];
            if (curSum < minSum) {
                minSum = curSum;
                bestRoom = i;
            }
        }
        cout << bestRoom << '\n';
    }
}