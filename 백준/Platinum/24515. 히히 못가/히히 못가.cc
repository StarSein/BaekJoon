/*
1. bfs => 영역 구분
2. 각 영역(노드)에 해당하는 좌표값을 바탕으로, 시작 지점 노드와 종료 지점 노드 찾아서 저장하기
3. 각 좌표마다 영역의 번호 부여하기
4. 각 노드의 인접 노드와 그 가중치 찾아서 양방향 인접 리스트 형태로 저장하기
    (가중치 = 해당 좌표가 속한 영역의 크기)
5. 다익스트라 => 간선 정보를 바탕으로 시작 지점 노드에서 종료 지점 노드까지 최단 거리 구하기
*/


#include <iostream>
#include <queue>
#include <unordered_set>
using namespace std;
typedef pair<int, int> pi;

int N;
vector<string> land;
vector<vector<bool>> visit;

vector<vector<pi>> regions, graph;
vector<vector<int>> regionIDs;
unordered_set<int> starts, terminals;

int dy[] {0, 1, 0, -1, 1, 1, -1, -1};
int dx[] {1, 0, -1, 0, 1, -1, 1, -1};

vector<pi> bfs(int sr, int sc) {
    const char &mark = land[sr][sc];
    vector<pi> ret;
    queue<pi> q;
    q.emplace(sr, sc);
    visit[sr][sc] = true;
    ret.emplace_back(sr, sc);
    while (!q.empty()) {
        const auto &[r, c] = q.front();
        for (int i = 0; i < 4; i++) {
            int nr = r + dy[i], nc = c + dx[i];
            if (0 <= nr && nr < N && 0 <= nc && nc < N 
            && land[nr][nc] == mark && !visit[nr][nc]) {
                q.emplace(nr, nc);
                visit[nr][nc] = true;
                ret.emplace_back(nr, nc);    
            }
        }
        q.pop();
    }
    return ret;
}

unordered_set<int> getAdjRegions(int id) {
    vector<pi> region = regions[id];
    unordered_set<int> ret;
    for (const auto &[r, c] : region) {
        for (int i = 0; i < 8; i++) {
            int nr = r + dy[i], nc = c + dx[i];
            if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                int nextID = regionIDs[nr][nc];
                if (ret.count(nextID) == 0) {
                    ret.insert(nextID);
                }
            }
        }
    }
    return ret;
}

int dijkstra(unordered_set<int> _starts) {
    priority_queue<pi, vector<pi>, greater<pi>> pq;
    vector<bool> visited(regions.size(), false);

    for (const int &node : _starts) {
        pq.emplace(regions[node].size(), node);
    }
    while (!pq.empty()) {
        const auto [curDist, curNode] = pq.top();
        pq.pop();

        if (terminals.count(curNode)) {
            return curDist;
        }
        
        if (visited[curNode]) {
            continue;
        }
        visited[curNode] = true;

        for (const auto &[nextNode, weight] : graph[curNode]) {
            pq.emplace(weight + curDist, nextNode);
        }
    }
    return -1;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    land.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> land[i];
    }

    visit.resize(N, vector<bool>(N, false));
    visit[0][0] = true;
    visit[N-1][N-1] = true;
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            if (!visit[r][c]) {
                regions.push_back(bfs(r, c));
            }
        }
    }

    for (int i = 0; i < regions.size(); i++) {
        for (const auto &[r, c] : regions[i]) {
            if (r == 0 || c == N - 1) {
                starts.insert(i);
            }
            if (r == N - 1 || c == 0) {
                terminals.insert(i);
            }
        }
    }

    regionIDs.resize(N, vector<int>(N));
    for (int id = 0; id < regions.size(); id++) {
        int area = regions[id].size();
        for (const auto &[r, c] : regions[id]) {
            regionIDs[r][c] = id;
        }
    }

    graph.resize(regions.size(), vector<pi>());
    for (int i = 0; i < regions.size(); i++) {
        const unordered_set<int> &adjRegions = getAdjRegions(i);
        for (int j : adjRegions) {
            graph[i].emplace_back(j, regions[j].size());
        }
    }

    int ans = dijkstra(starts);
    cout << ans;
    return 0;
}