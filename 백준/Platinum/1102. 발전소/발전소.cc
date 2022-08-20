#include <iostream>
#include <algorithm>
using namespace std;
#define INF static_cast<int>(1e5)


const int MAX_N = 16;
int graph[MAX_N][MAX_N], dpW[MAX_N][1 << MAX_N], dpC[1 << MAX_N];

int N, P;


int getMinWeight(int fixed, int nextNode) {
    int &ret = dpW[nextNode][fixed];
    if (ret != INF) {
        return ret;
    }
    for (int curNode = 0; curNode < N; curNode++) {
        if (fixed & 1 << curNode) {
            ret = min(ret, graph[curNode][nextNode]);
        }
    }
    return ret;
}


int getMinCost(int fixed, int cntFixed) {
    if (cntFixed >= P) {
        return 0;
    }
    if (fixed == 0) {
        return -1;
    }
    int &ret = dpC[fixed];
    if (ret != INF) {
        return ret;
    }
    for (int nextNode = 0; nextNode < N; nextNode++) {
        if (!(fixed & 1 << nextNode)) {
            ret = min(ret, getMinWeight(fixed, nextNode) + getMinCost(fixed | 1 << nextNode, cntFixed + 1));
        }
    }
    return ret;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    fill(dpC, dpC + (1 << MAX_N), INF);
    fill(&dpW[0][0], &dpW[0][0] + MAX_N * (1 << MAX_N), INF);

    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> graph[i][j];
        }
    }
    string s; cin >> s;
    int initBit = 0, cntY = 0;
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == 'Y') {
            initBit |= 1 << i;
            cntY++;
        }
    }
    cin >> P;
    cout << getMinCost(initBit, cntY);
    return 0;
}