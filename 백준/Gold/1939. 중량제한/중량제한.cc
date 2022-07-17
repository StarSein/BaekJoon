#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 1e5+1;
vector<vector<pi>> graph;
int n, m;
int s, e;

queue<int> q;
bool visit[MAX_N];

bool pSearch(int weight) {
    memset(visit, 0, sizeof(visit));
    q.push(s);
    visit[s] = true;
    while (!q.empty()) {
        int curNode = q.front();
        if (curNode == e) {
            while (!q.empty()) {
                q.pop();
            }
            return true;
        }
        q.pop();
        for (auto &[nextNode, limit] : graph[curNode]) {
            if (limit >= weight && !visit[nextNode]) {
                q.push(nextNode);
                visit[nextNode] = true;
            }
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    cin >> n >> m;
    graph.resize(n+1, vector<pi>());
    for (int i = 0; i < m; i++) {
        int a, b, c; cin >> a >> b >> c;
        graph[a].emplace_back(b, c);
        graph[b].emplace_back(a, c);
    }
    cin >> s >> e;
    int left = 1, right = 1e9;
    int ans;
    while (left <= right) {
        int mid = (left + right) >> 1;
        if (pSearch(mid)) {
            ans = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    cout << ans;
}