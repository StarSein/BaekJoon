#include <iostream>
#include <set>
#include <unordered_map>
#include <utility>
#include <vector>
using namespace std;
#define X first
#define Y second
#define pi pair<int, int>
#define pb push_back

const int NINF = (int)-1e9;
int N, M, whiteNodeCount;
vector<int> depth, parent, subTreeSize, distFromRoot;
vector<vector<int>> ancestor;
vector<bool> isCut, isWhite;
vector<vector<pi>> graph;
vector<unordered_map<int, multiset<int>>> tm1;
vector<multiset<int>> tm2;
multiset<int> tm3;

void dfs(int curNode, int parNode, int dist);
void makeCentroidTree(int root, int prevCentroid);
int getSubTreeSize(int curNode, int parNode);
int getCentroid(int curNode, int parNode, int halfSize);
int getDist(int i, int p);
int getLCA(int i, int p);
int getDiameter(multiset<int> &ms);
void toggleColor(int node);

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;
    graph.resize(N + 1);
    for (int i = 1; i < N; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        graph[u].emplace_back(v, w);
        graph[v].emplace_back(u, w);
    }

    depth.resize(N + 1);
    ancestor.resize(18, vector<int>(N + 1));
    distFromRoot.resize(N + 1);
    dfs(1, 0, 0);

    for (int i = 1; i < ancestor.size(); i++) {
        for (int j = 1; j <= N; j++) {
            int mid = ancestor[i - 1][j];
            ancestor[i][j] = ancestor[i - 1][mid];
        }
    }

    subTreeSize.resize(N + 1);
    parent.resize(N + 1);
    isCut.resize(N + 1, false);
    makeCentroidTree(1, 0);

    isWhite.resize(N + 1, false);
    tm1.resize(N + 1);
    for (int i = 1; i <= N; i++) {
        tm1[parent[i]][i] = multiset<int>();
    }
    tm2.resize(N + 1);

    for (int i = 1; i <= N; i++) {
        toggleColor(i);
    }

    cin >> M;
    for (int i = 0; i < M; i++) {
        int cmd;
        cin >> cmd;
        if (cmd == 1) {
            int node;
            cin >> node;
            toggleColor(node);
        } else {
            int ans;
            if (whiteNodeCount <= 1) {
                ans = whiteNodeCount - 1;
            } else {
                ans = max(0, *tm3.rbegin());
            }
            cout << ans << '\n';
        }
    }

    return 0;
}

void dfs(int curNode, int parNode, int dist) {
    depth[curNode] = depth[parNode] + 1;
    ancestor[0][curNode] = parNode;
    distFromRoot[curNode] = dist;

    for (auto &[nextNode, weight] : graph[curNode]) {
        if (nextNode == parNode) {
            continue;
        }
        dfs(nextNode, curNode, dist + weight);
    }
}
void makeCentroidTree(int root, int prevCentroid) {
    int treeSize = getSubTreeSize(root, 0);
    int centroid = getCentroid(root, 0, treeSize / 2);

    parent[centroid] = prevCentroid;
    isCut[centroid] = true;

    for (auto &[nextNode, _] : graph[centroid]) {
        if (isCut[nextNode]) {
            continue;
        }
        makeCentroidTree(nextNode, centroid);
    }
}
int getSubTreeSize(int curNode, int parNode) {
    int size = 1;
    for (auto &[nextNode, _] : graph[curNode]) {
        if (isCut[nextNode]) {
            continue;
        }
        if (nextNode == parNode) {
            continue;
        }
        size += getSubTreeSize(nextNode, curNode);
    }
    return subTreeSize[curNode] = size;
}
int getCentroid(int curNode, int parNode, int halfSize) {
    for (auto &[nextNode, _] : graph[curNode]) {
        if (isCut[nextNode]) {
            continue;
        }
        if (nextNode == parNode) {
            continue;
        }
        if (subTreeSize[nextNode] > halfSize) {
            return getCentroid(nextNode, curNode, halfSize);
        }
    }
    return curNode;
}
int getDist(int i, int p) {
    int lca = getLCA(i, p);
    return distFromRoot[i] + distFromRoot[p] - 2 * distFromRoot[lca];
}
int getLCA(int i, int p) {
    if (depth[i] < depth[p]) {
        int temp = i;
        i = p;
        p = temp;
    }

    int diff = depth[i] - depth[p];
    for (int j = ancestor.size() - 1; j >= 0; j--) {
        if (diff & (1 << j)) {
            i = ancestor[j][i];
        }
    }

    if (i == p) {
        return i;
    }

    for (int j = ancestor.size() - 1; j >= 0; j--) {
        if (ancestor[j][i] != ancestor[j][p]) {
            i = ancestor[j][i];
            p = ancestor[j][p];
        }
    }

    return ancestor[0][i];
}
int getDiameter(multiset<int> &set) {
    if (set.empty()) {
        return NINF;
    }
    int firstMax = *set.rbegin();
    if (set.size() == 1) {
        return firstMax;
    }
    int secondMax = *next(set.rbegin());
    return max(firstMax, firstMax + secondMax);
}
void toggleColor(int node) {
    isWhite[node] = !isWhite[node];
    whiteNodeCount += isWhite[node] ? 1 : -1;

    int q = node;
    for (int p = parent[node]; p != 0; p = parent[p]) {
        int dist = getDist(node, p);

        int oldDia = getDiameter(tm2[p]);

        int oldMaxKey = tm1[p][q].empty() ? NINF : *(tm1[p][q].rbegin());

        if (isWhite[node]) {
            tm1[p][q].insert(dist);
        } else {
            tm1[p][q].erase(tm1[p][q].find(dist));
        }

        int newMaxKey = tm1[p][q].empty() ? NINF : *(tm1[p][q].rbegin());
        if (oldMaxKey != newMaxKey) {
            if (oldMaxKey != NINF) {
                tm2[p].erase(tm2[p].find(oldMaxKey));
            }
            if (newMaxKey != NINF) {
                tm2[p].insert(newMaxKey);
            }
        }

        int newDia = getDiameter(tm2[p]);
        if (oldDia != newDia) {
            if (oldDia != NINF) {
                tm3.erase(tm3.find(oldDia));
            }
            if (newDia != NINF) {
                tm3.insert(newDia);
            }
        }

        q = p;
    }
}