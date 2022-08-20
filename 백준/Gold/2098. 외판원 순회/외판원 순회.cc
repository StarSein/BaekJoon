#include <iostream>
using namespace std;


const int MAX_N = 16, INF = 1e8, START = 0;
int N;
int graph[MAX_N][MAX_N], dp[MAX_N][1 << MAX_N];


int getMinCost(int curNode, int curBit) {
    int xBit = ((1 << N) - 1) & ~curBit;
    int &ret = dp[curNode][xBit];
    if (xBit == 0) {
        return ret = graph[curNode][START];
    }
    if (ret) {
        return ret;
    }
    ret = INF;
    for (int i = 0; i < N; i++) {
        if (xBit & (1 << i) && graph[curNode][i] != INF) {
            ret = min(ret, graph[curNode][i] + getMinCost(i, curBit | (1 << i)));
        }
    }
    return ret;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> graph[i][j];

            if (graph[i][j] == 0) {
                graph[i][j] = INF;
            }
        }
    }

    cout << getMinCost(START, 1 << START);
    return 0;
}