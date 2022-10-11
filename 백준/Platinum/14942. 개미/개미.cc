#include <iostream>
#include <vector>
using namespace std;
typedef pair<int, int> pi;

const int ROOT = 1, LOG = 17;
vector<int> energies; 
vector<vector<pi>> graph, table;

void dfs(int curNode, int parentNode) {
    for (auto &[nextNode, weight] : graph[curNode]) {
        if (nextNode != parentNode) {
            table[nextNode][0] = {curNode, weight};
            dfs(nextNode, curNode);
        }
    }
}

int getAns(int node, int energy) {
    while (true) {
        if (node == ROOT) {
            break;
        }

        bool flag = true;
        int exp = LOG - 1;
        int bit = 1 << exp;
        while (bit) {
            auto &[nextNode, cost] = table[node][exp];
            if (nextNode != -1 && cost <= energy) {
                energy -= cost;
                node = nextNode;
                flag = false;
                break;
            }
            exp--;
            bit >>= 1;
        }

        if (flag) {
            break;
        }
    }
    return node;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    energies.resize(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> energies[i];
    }
    graph.resize(n + 1);
    for (int i = 1; i < n; i++) {
        int a, b, c; cin >> a >> b >> c;
        graph[a].emplace_back(b, c);
        graph[b].emplace_back(a, c);
    }

    table.resize(n + 1, vector<pi>(LOG, {-1, -1}));
    dfs(ROOT, 0);
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j < LOG; j++) {
            auto &[midNode, midLength] = table[i][j-1];
            if (midNode == -1) {
                continue;
            }
            auto &[endNode, endLength] = table[midNode][j-1];
            if (endNode == -1) {
                continue;
            }
            table[i][j] = {endNode, midLength + endLength};
        }
    }
    for (int i = 1; i <= n; i++) {
        cout << getAns(i, energies[i]) << '\n';
    }
    return 0;
}