#include <iostream>
#include <vector>
#include <queue>
using namespace std;

typedef struct Node {
    int r, c, d, cnt;

    Node() = default;
    Node(int r, int c, int d, int cnt) : r(r), c(c), d(d), cnt(cnt) {};
} Node;

int n;
const int INF = 2500;
vector<string> grid;
vector<vector<vector<int>>> cache;

vector<int> dy {0, 1, 0, -1};
vector<int> dx {1, 0, -1, 0};

queue<Node> q;

inline bool isProperIdx(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < n;
}
int bfs(int r, int c) {
    int answer = INF;
    for (int i = 0; i < 4; i++) {
        q.emplace(r, c, i, 0);
        cache[r][c][i] = true;
    }
    
    Node cur;
    int nr = -1, nc = -1, nd = -1;
    while (!q.empty()) {
        cur = q.front();
        q.pop();

        if (grid[cur.r][cur.c] == '#' && !(cur.r == r && cur.c == c)) {
            answer = min(answer, cur.cnt);
            continue;
        }

        nr = cur.r + dy[cur.d];
        nc = cur.c + dx[cur.d];
        if (isProperIdx(nr, nc) && grid[nr][nc] != '*' && cur.cnt < cache[nr][nc][cur.d]) {
            q.emplace(nr, nc, cur.d, cur.cnt);
            cache[nr][nc][cur.d] = cur.cnt;
        }

        if (grid[cur.r][cur.c] == '!') {
            nd = cur.d == 3 ? 0 : cur.d + 1;
            nr = cur.r + dy[nd];
            nc = cur.c + dx[nd];
            if (isProperIdx(nr, nc) && grid[nr][nc] != '*' && cur.cnt < cache[nr][nc][nd]) {
                q.emplace(nr, nc, nd, cur.cnt + 1);
                cache[nr][nc][nd] = cur.cnt;
            }
            nd = cur.d == 0 ? 3 : cur.d - 1;
            nr = cur.r + dy[nd];
            nc = cur.c + dx[nd];
            if (isProperIdx(nr, nc) && grid[nr][nc] != '*' && cur.cnt < cache[nr][nc][nd]) {
                q.emplace(nr, nc, nd, cur.cnt + 1);
                cache[nr][nc][nd] = cur.cnt;
            }
        }
    }
    return answer;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    grid.resize(n);
    cache.resize(n, vector<vector<int>>(n, vector<int>(4, INF)));
    for (int i = 0; i < n; i++)
        cin >> grid[i];
    
    bool flag = false;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == '#') {
                cout << bfs(i, j);
                flag = true;
                break;
            }
        }
        if (flag)
            break;
    }
    return 0;
}