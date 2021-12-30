#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <queue>
#include <stack>
#include <algorithm>
using namespace std;

const int ROOT_NUM = 1;
const int MAX_N = 10000;
int maxDiameter = 0;
enum KEY { PARENT, WEIGHT, MAX1_LENGTH, MAX2_LENGTH, IS_INTERNAL };
int table[MAX_N + 1][5] = { 0 };
map<int, vector<int>> m_child;
stack<int> st_orderOfUpdate;

void input() {
    int n;
    cin >> n;
    for (int i = 0; i < n - 1; i++) {
        int parentNum, childNum, weight;
        cin >> parentNum >> childNum >> weight;
        
        table[childNum][PARENT] = parentNum;
        table[childNum][WEIGHT] = weight;
        if (m_child.find(parentNum) == m_child.end()) {
            m_child[parentNum] = {childNum};
            table[parentNum][IS_INTERNAL] = 1;
        } else {
            m_child[parentNum].push_back(childNum);
        }
    }
}
void makePlan() {
    queue<int> q_preOrder;
    q_preOrder.push(ROOT_NUM);
    while (!q_preOrder.empty()) {
        int currentNum = q_preOrder.front();
        q_preOrder.pop();

        if (!table[currentNum][IS_INTERNAL])
            continue;

        st_orderOfUpdate.push(currentNum);
        for (int childNum : m_child[currentNum])
            q_preOrder.push(childNum);
    }
}
void updateMaxLength(int nodeNum) {
    set<int, greater<int>> s_distToLeaf;
    for (int childNum : m_child[nodeNum]) {
        s_distToLeaf.insert(table[childNum][WEIGHT] + table[childNum][MAX1_LENGTH]);
    }
    set<int, greater<int>>::iterator iter = s_distToLeaf.begin();
    table[nodeNum][MAX1_LENGTH] = *iter;
    table[nodeNum][MAX2_LENGTH] = *(++iter);
}
void updateMaxDiameter(int nodeNum) {
    int currentDiameter = 0;
    if (m_child[nodeNum].size() == 1)
        currentDiameter = table[nodeNum][MAX1_LENGTH];
    else
        currentDiameter = table[nodeNum][MAX1_LENGTH]
                        + table[nodeNum][MAX2_LENGTH];
    maxDiameter = max(maxDiameter, currentDiameter);
}
void divideAndConquer() {
    makePlan();
    while (!st_orderOfUpdate.empty()) {
        int currentNum = st_orderOfUpdate.top();
        st_orderOfUpdate.pop();
        updateMaxLength(currentNum);
        updateMaxDiameter(currentNum);
    }
}
void output() {
    cout << maxDiameter;
}
int main() {
    input();
    divideAndConquer();
    output();
    return 0;
}
