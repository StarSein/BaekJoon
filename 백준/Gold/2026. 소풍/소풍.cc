#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
#include <iterator>
using namespace std;

const int MAX_N = 900;
bool flag = false;
int k, n, f;
vector<vector<int>> graph(MAX_N + 1, vector<int>());
vector<int> answer;

void dfs(int curNode) {
    if (flag)
        return;

    answer.push_back(curNode);
    if (answer.size() == k) {
        flag = true;
        copy(answer.begin(), answer.end(), ostream_iterator<int>(cout, "\n"));
        return;
    }
    for (int nextNode : graph[curNode]) {
        int i = 0, j = 0;
        while (i < answer.size() && j < graph[nextNode].size() && answer[i] >= graph[nextNode][j]) {
            if (answer[i] == graph[nextNode][j]) {
                i++;
            }
            j++;
        }
        if (i == answer.size())
            dfs(nextNode);
    }
    if (answer.size() == k)
        return;
    answer.pop_back();
}

int main() {
    cin >> k >> n >> f;
    int u, v;
    while (f--) {
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    for (int i = 1; i <= n; i++)
        sort(graph[i].begin(), graph[i].end());
    answer.reserve(k);
    for (int i = 1; i <= n; i++)
        dfs(i);
    if (!flag)
        cout << -1;
}