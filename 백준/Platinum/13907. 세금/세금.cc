#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
using namespace std;
#define INF (int) 1e9
#define MAX_N (int) 1e3 + 1
typedef pair<int, int> pi;

struct Node {
    int cost;
    int edgeCnt;
    int node;

    Node() = default;
    Node(int c, int e, int n): cost(c), edgeCnt(e), node(n) {};
};

struct cmp {
    bool operator() (Node &a, Node &b) {
        return a.cost > b.cost;
    }
};

int dist[MAX_N][MAX_N];
priority_queue<Node, vector<Node>, cmp> pq;

int N, M, K;
int S, D;
vector<pi> graph[MAX_N];

int tax = 0;

void dijkstra() {
    pq.emplace(0, 0, S);
    while (!pq.empty()) {
        Node cur = pq.top();
        pq.pop();
        
        if (dist[cur.node][cur.edgeCnt] < cur.cost) {
            continue;
        }

        if (++(cur.edgeCnt) < N) {
            for (const auto &[nextNode, weight]: graph[cur.node]) {
                if (dist[nextNode][cur.edgeCnt] > cur.cost + weight) {
                    dist[nextNode][cur.edgeCnt] = cur.cost + weight;
                    pq.emplace(cur.cost + weight, cur.edgeCnt, nextNode);
                }
            }
        }
    }
}

int getAns() {
    int ret = INF;
    for (int edgeCnt = 1; edgeCnt < N; edgeCnt++) {
        ret = min(ret, dist[D][edgeCnt] + tax * edgeCnt);
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M >> K;
    cin >> S >> D;
    
    for (int i = 0; i < M; i++) {
        int a, b, w; cin >> a >> b >> w;
        graph[a].emplace_back(b, w);
        graph[b].emplace_back(a, w);
    }

    for (int i = 0; i < MAX_N; i++) {
        for (int j = 0; j < MAX_N; j++) {
            dist[i][j] = INF;
        }
    }
    
    dijkstra();
    
    cout << getAns() << '\n';
    for (int i = 0; i < K; i++) {
        int p; cin >> p;
        tax += p;
        cout << getAns() << '\n';
    }
    return 0;
}