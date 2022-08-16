#include <iostream>
#include <stack>
#include <vector>
#include <unordered_set>
using namespace std;
typedef pair<int, int> pi;

vector<vector<int>> graph;
vector<int> digraph;
vector<bool> visit, able;
vector<pi> minMaxCompo;

int getSizeCompo(int node) {
    stack<int> st;
    int ret = 0;
    st.push(node);
    visit[node] = true;
    while (!st.empty()) {
        int curNode = st.top();
        st.pop();

        ret++;

        for (int &nextNode : graph[curNode]) {
            if (!visit[nextNode]) {
                st.push(nextNode);
                visit[nextNode] = true;
            }
        }
    }
    return ret;
}

int getSizeCycle(int node) {
    unordered_set<int> visited;
    stack<int> st;
    while (!visited.count(node)) {
        st.push(node);
        visited.insert(node);

        node = digraph[node];
    }
    int ret = 1;
    while (!st.empty() && st.top() != node) {
        st.pop();
        ret++;
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int n, k; cin >> n >> k;
    
    graph.resize(n + 1);
    digraph.resize(n + 1);
    visit.resize(n + 1);
    able.resize(k + 1);

    for (int i = 1; i <= n; i++) {
        int adj; cin >> adj;

        graph[i].push_back(adj);
        graph[adj].push_back(i);
        digraph[i] = adj;
    }

    for (int i = 1; i <= n; i++) {
        if (!visit[i]) {
            int sizeComponent = getSizeCompo(i);
            int sizeCycle = getSizeCycle(i);
            
            minMaxCompo.emplace_back(sizeCycle, sizeComponent);
        }
    }

    able[0] = true;
    for (auto &[minVal, maxVal] : minMaxCompo) {
        for (int i = k; i >= 0; i--) {
            if (able[i]) {
                for (int j = minVal; j <= maxVal && i + j <= k; j++) {
                    able[i + j] = true;
                }
            }
        }
    }

    for (int i = k; i >= 0; i--) {
        if (able[i]) {
            cout << i;
            break;
        }
    }
    return 0;
}