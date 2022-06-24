#include <iostream>
#include <vector>
#include <set>
#include <stack>
using namespace std;

int n, m;
vector<set<int>> vSetIn, vSetBoth;
vector<bool> visit;
vector<int> check;
stack<int> st;

void dfs1(int curr) {
    visit[curr] = true;
    for (auto iter = vSetBoth[curr].begin(); iter != vSetBoth[curr].end(); iter++) {
        if (!visit[*iter]) {
            dfs1(*iter);
        }
    }
}
void dfs2(int curr, int prev) {
    visit[curr] = true;
    st.push(curr);
    for (auto iter = vSetBoth[curr].begin(); iter != vSetBoth[curr].end(); iter++) {
        if (*iter != prev) {
            if (!visit[*iter]) {
                dfs2(*iter, curr);
            } else {
                st.push(*iter);
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m;
    vSetIn.resize(n+1, set<int>());
    vSetBoth.resize(n+1, set<int>());
    visit.resize(n+1, false);
    check.resize(n+1, 0);

    int a, b;
    for (int i = 0; i < m; i++) {
        cin >> a >> b;
        if (vSetIn[a].find(b) != vSetIn[a].end()) {
            vSetIn[a].erase(b);
            vSetBoth[a].insert(b);
            vSetBoth[b].insert(a);
        } else {
            vSetIn[b].insert(a);
        }
    }

    for (int node = 1; node <= n; node++) {
        if (vSetIn[node].size()) {
            visit[node] = true;
        }
    }

    for (int node = 1; node <= n; node++) {
        if (visit[node]) {
            dfs1(node);
        } 
    }
    for (int node = 1; node <= n; node++) {
        if (!visit[node] && !vSetBoth[node].empty()) {
            dfs2(node, 0);
            bool isCycle = false;
            while (!st.empty()) {
                if (check[st.top()]++) {
                    isCycle = true;
                }
                st.pop();
            }
            if (!isCycle) {
                cout << "NO";
                return 0;
            }
        }
    }
    for (int node = 1; node <= n; node++) {
        if (!visit[node]) {
            cout << "NO";
            return 0;
        }
    }
    cout << "YES";
    return 0;
}