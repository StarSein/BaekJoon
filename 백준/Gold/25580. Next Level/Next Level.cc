#include <bits/stdc++.h>
using namespace std;

typedef struct info {
    string need;
    string learn;
} info;


const int MAX_N = 4, MAX_ALGO = 10, MAX_LEVEL = 16, MAX_NODE = 88;
const int DEADLINE = 18;
int N;
string grid[MAX_N];
int levelStd[MAX_N][MAX_N];
int algoCnt = 0;
unordered_map<string, int> algoToBit;
unordered_map<int, info> infoBoss;

vector<unordered_map<int, int>> graph;
bool visited[MAX_NODE + 1];

int KOSMO;
bool avail;


void drawEdge(int a, int b, int l) {
    if (graph[a].count(b) == 0 || l > graph[a][b]) {
        graph[a][b] = l;
    }
    if (graph[b].count(a) == 0 || l > graph[b][a]) {
        graph[b][a] = l;
    }
}

void makeGraph() {
    graph.resize(MAX_NODE + 1);
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            int level = levelStd[r][c];
            int leftTop = 20 * r + 2 * c;
            drawEdge(leftTop, leftTop + 1, level);
            drawEdge(leftTop + 1, leftTop + 2, level);
            drawEdge(leftTop, leftTop + 10, level);
            drawEdge(leftTop + 10, leftTop + 20, level);
            drawEdge(leftTop + 2, leftTop + 12, level);
            drawEdge(leftTop + 12, leftTop + 22, level);
            drawEdge(leftTop + 20, leftTop + 21, level);
            drawEdge(leftTop + 21, leftTop + 22, level);

            if (grid[r][c] == 'A') {
                drawEdge(leftTop + 1, leftTop + 11, level);
                drawEdge(leftTop + 10, leftTop + 11, level);
                drawEdge(leftTop + 21, leftTop + 11, level);
                drawEdge(leftTop + 12, leftTop + 11, level);
            } else if (grid[r][c] == 'B') {
                drawEdge(leftTop + 10, leftTop + 11, level);
                drawEdge(leftTop + 12, leftTop + 11, level);
            } else if (grid[r][c] == 'C') {
                drawEdge(leftTop + 1, leftTop + 11, level);
                drawEdge(leftTop + 21, leftTop + 11, level);
            } else {
                drawEdge(leftTop, leftTop + 11, level);
                drawEdge(leftTop + 2, leftTop + 11, level);
                drawEdge(leftTop + 20, leftTop + 11, level);
                drawEdge(leftTop + 22, leftTop + 11, level);
            }
        }
    }
}

void dfs(int day, int node, int level, int mask) {
    if (avail || day > DEADLINE) {
        return;
    }

    if (node == KOSMO) {
        avail = true;
        return;
    }

    if (infoBoss.count(node)) {
        info cur = infoBoss[node];
        if (cur.need != "0" && (mask & (1 << algoToBit[cur.need])) || cur.need == "0") {
            if (cur.learn != "0") {
                mask |= 1 << algoToBit[cur.learn];
            }
            if (level < MAX_LEVEL) {
                level++;
            }
        } else {
            return;
        }
    }

    for (auto &[nextNode, levelCond] : graph[node]) {
        if (level >= levelCond && !visited[nextNode]) {
            visited[nextNode] = true;
            dfs(day + 1, nextNode, level, mask);
            visited[nextNode] = false;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    for (int r = 0; r < N; r++) {
        cin >> grid[r];
    }
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            cin >> levelStd[r][c];
        }
    }

    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            int node = 20 * r + 2 * c + 11;
            bool e; string n, l; cin >> e >> n >> l;
            if (n != "0" && !algoToBit.count(n)) {
                algoToBit[n] = algoCnt++;
            }
            if (l != "0" && !algoToBit.count(l)) {
                algoToBit[l] = algoCnt++;
            }
            if (e) {
                infoBoss[node] = {n, l};
            }
        }
    }
    int a; cin >> a;
    int initBit = 0;
    for (int i = 0; i < a; i++) {
        string algo; cin >> algo;
        if (!algoToBit.count(algo)) {
            algoToBit[algo] = algoCnt++;
        }
        initBit |= 1 << algoToBit[algo];
    }

    KOSMO = 20 * N + 2 * N;
    makeGraph();
    visited[0] = true;
    dfs(0, 0, 1, initBit);
    
    cout << (avail ? "Dreams Come True" : "-1");
    return 0;
}