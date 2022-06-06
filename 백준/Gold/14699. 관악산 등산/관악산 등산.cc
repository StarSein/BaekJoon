#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <iterator>
using namespace std;

vector<int> vHeight;
vector<int> numPreceder;
vector<vector<int>> follower;
vector<int> answer;
queue<int> qNode;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    int n, m; cin >> n >> m;
    vHeight.resize(n + 1, 0);
    for (int i = 1; i <= n; i++)
        cin >> vHeight[i];
    
    numPreceder.resize(n + 1, 0);
    follower.resize(n + 1, vector<int>());
    int u, v;
    for (int i = 0; i < m; i++) {
        cin >> u >> v;
        if (vHeight[u] > vHeight[v]) {
            follower[u].push_back(v);
            numPreceder[v]++;
        }
        else {
            follower[v].push_back(u);
            numPreceder[u]++;
        }
    }

    answer.resize(n + 1, 0);
    for (int i = 1; i <= n; i++)
        if (numPreceder[i] == 0) {
            qNode.push(i);
            answer[i] = 1;
        }
    while (!qNode.empty()) {
        int curNode = qNode.front();
        qNode.pop();
        for (int& nextNode : follower[curNode]) {
            numPreceder[nextNode]--;
            if (numPreceder[nextNode] == 0) {
                answer[nextNode] = answer[curNode] + 1;
                qNode.push(nextNode);
            }
        }
    }
    copy(next(answer.begin(), 1), answer.end(), ostream_iterator<int>(cout, "\n"));
}