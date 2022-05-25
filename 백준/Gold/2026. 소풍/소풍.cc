#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
#include <iterator>
using namespace std;

const int MAX_N = 900;
int k, n, f;
vector<vector<bool>> graph(MAX_N + 1, vector<bool>(MAX_N + 1, false));
vector<int> answer;

bool dfs(int curNode) {
    answer.push_back(curNode);
    if (answer.size() == k)     
        return true;

    for (int nextNode = curNode + 1; nextNode <= n; nextNode++) {
        if (graph[curNode][nextNode]) {
            bool flag = true;
            for (int frd : answer) {
                if (!graph[nextNode][frd]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (dfs(nextNode))
                    return true;
            }
        }
    }
    answer.pop_back();
    return false;
}

int main() {
    cin >> k >> n >> f;
    int u, v;
    while (f--) {
        cin >> u >> v;
        graph[u][v] = true;
        graph[v][u] = true;
    }
    answer.reserve(k);
    for (int i = 1; i <= n; i++) {
        if (dfs(i)) {
            copy(answer.begin(), answer.end(), ostream_iterator<int>(cout, "\n"));
            return 0;
        }
    }
    cout << -1;
}