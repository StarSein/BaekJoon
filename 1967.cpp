#include <iostream>
#include <vector>
#include <queue>
#include <stack>
#include <set>
#include <algorithm>
using namespace std;
#define pi pair<int, int>

class Node {
public:
    int parent = 0;
    int weight = 0;
    int maxDistToLeaf = 0;
    int secDistToLeaf = 0;

    Node() : parent(0) {}
};

int n;
const int ROOT_NUM = 1;
int maxDiameter = 0;
vector<Node*> v_node;
vector<bool> v_isLeaf;
vector<vector<int>> vv_child;
stack<int> st_orderOfUpdate;

void input() {
    cin >> n;
    for (int i = 0; i < n + 1; i++) {
        v_node.push_back(new Node());
    }
    int parentNum, childNum, weight;
    v_isLeaf.assign(n+1, true);
    vv_child.assign(n+1, {});
    for (int i = 0; i < n - 1; i++) {
        cin >> parentNum >> childNum >> weight;
        v_node[childNum]->parent = parentNum;
        v_node[childNum]->weight = weight;
        vv_child[parentNum].push_back(childNum);
        v_isLeaf[parentNum] = false;
    }
}
void makeUpdatePlan() {
    queue<int> q_preOrder;
    q_preOrder.push(ROOT_NUM);
    while (!q_preOrder.empty()) {
        int currentNode = q_preOrder.front();
        q_preOrder.pop();
        if (v_isLeaf[currentNode])
            continue;
        
        st_orderOfUpdate.push(currentNode);
        for (int childNode : vv_child[currentNode])
            q_preOrder.push(childNode);
    }
}
void updateMaxDistToLeaf(int nodeNum) {
    Node* node = v_node[nodeNum];
    set<int, greater<int>> s_distToLeaf;
    int maxDistToLeaf = 0, weight = 0;
    for (int child : vv_child[nodeNum]) {
        maxDistToLeaf = (v_node[child]->maxDistToLeaf);
        weight = (v_node[child]->weight);
        s_distToLeaf.insert(maxDistToLeaf + weight);
    }
    
    set<int, greater<int>>::iterator iter = s_distToLeaf.begin();
    node->maxDistToLeaf = *iter;
    node->secDistToLeaf = *(++iter);
}
void updateMaxDiameter(int nodeNum) {
    Node* node = v_node[nodeNum];
    int maxLengthOfRoute = 0;
    if (vv_child[nodeNum].size() == 1) {
        maxLengthOfRoute = node->maxDistToLeaf;
    } else {
        maxLengthOfRoute = node->maxDistToLeaf
                         + node->secDistToLeaf;
    }
    maxDiameter = max(maxDiameter, maxLengthOfRoute);
}
void compute() {
    makeUpdatePlan();
    while (!st_orderOfUpdate.empty()) {
        int currentNode = st_orderOfUpdate.top();
        st_orderOfUpdate.pop();
        updateMaxDistToLeaf(currentNode);
        updateMaxDiameter(currentNode);
    }
}
void output() {
    cout << maxDiameter;
}
int main() {
    input();
    compute();
    output();
    return 0;
}