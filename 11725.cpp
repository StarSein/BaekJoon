#include <iostream>
#include <vector>
#include <queue>
using namespace std;
#define ROOT_NUM 1
#define pi pair<int, int>
#define X first
#define Y second


int n;
vector<int> v_parent;
vector<pi> v_pi;
queue<int> q_num;
void Input() {
    cin >> n;
    int nodeA, nodeB;
    for (int i = 0; i < n - 1; i++) {
        cin >> nodeA >> nodeB;
        v_pi.push_back(make_pair(nodeA, nodeB));
    }
}
void Compute() {
    v_parent.assign(n+1, -1);
    q_num.push(ROOT_NUM);
    while (!q_num.empty()) {
        int searchTarget = q_num.front();
        int child;
        for (pi e : v_pi) {
            if (e.X == searchTarget && v_parent[e.Y] == -1) {
                child = e.Y;
                q_num.push(child);
                v_parent[e.Y] = e.X;
            }
            if (e.Y == searchTarget && v_parent[e.X] == -1) {
                child = e.X;
                q_num.push(child);
                v_parent[e.X] = e.Y;
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