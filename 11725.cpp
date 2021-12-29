#include <iostream>
#include <vector>
#include <queue>
#include <map>
using namespace std;
#define ROOT_NUM 1


int n;
vector<int> v_parent;
map<int, vector<int>> m_connect;
queue<int> q_num;
void Input() {
    cin >> n;
    int nodeA, nodeB;
    for (int i = 0; i < n - 1; i++) {
        cin >> nodeA >> nodeB;
        if (m_connect.find(nodeA) == m_connect.end())
            m_connect[nodeA] = {nodeB};
        else
            m_connect[nodeA].push_back(nodeB);                                          
        if (m_connect.find(nodeB) == m_connect.end())
            m_connect[nodeB] = {nodeA};
        else
            m_connect[nodeB].push_back(nodeA);
    }
}
void Compute() {
    v_parent.assign(n+1, -1);
    q_num.push(ROOT_NUM);
    while (!q_num.empty()) {
        int searchTarget = q_num.front();
        int child;
        for (int e : m_connect[searchTarget]) {
            if (v_parent[e] == -1) {
                child = e;
                q_num.push(child);
                v_parent[child] = searchTarget;
            }
        }
        q_num.pop();
    }
}
void Output() {
    for (int i = 2; i < n + 1; i++) {
        cout << v_parent[i] << '\n';
    }
}
int main() {
    Input();
    Compute();
    Output();
    return 0;
}
