#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
using namespace std;
typedef pair<int, int> pi;

const int SZ = 100, NUM_CHAR = 26;
vector<string> grid;

bool visited[SZ][SZ];
bool key[NUM_CHAR];
vector<pi> savePoints[NUM_CHAR];

queue<pi> q;
int ans;

pi d[4] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};


void manageNewSpot(int r, int c) {
    visited[r][c] = true;
    if (grid[r][c] == '*') { 
        return;
    }
    if (grid[r][c] == '.') {
        q.emplace(r, c);
        return;
    }
    if (grid[r][c] == '$') {
        q.emplace(r, c);
        ans++;
        return;
    }
    if ('a' <= grid[r][c] && grid[r][c] <= 'z') {
        q.emplace(r, c);
        int idx = grid[r][c] - 'a';
        key[idx] = true;
        if (!savePoints[idx].empty()) {
            for (auto &[sr, sc] : savePoints[idx]) {
                q.emplace(sr, sc);
            }
            savePoints[idx].clear();
        }
        return;
    }
    int idx = grid[r][c] - 'A';
    if (key[idx]) {
        q.emplace(r, c);
    } else {
        savePoints[idx].emplace_back(r, c);
    }
}


void solution() {
    memset(visited, 0, sizeof(visited));
    memset(key, 0, sizeof(key));

    for (int i = 0; i < NUM_CHAR; i++) {
        savePoints[i].clear();
    }
    
    int h, w; cin >> h >> w;
    grid.resize(h);
    for (int r = 0; r < h; r++) {
        cin >> grid[r];
    }
    string initKey; cin >> initKey;

    if (initKey != "0") {
        for (char &c : initKey) {
            key[c - 'a'] = true;
        }
    }

    for (int r = 0; r < h; r++) {
        manageNewSpot(r, 0);
        manageNewSpot(r, w - 1);
    }

    for (int c = 1; c < w - 1; c++) {
        manageNewSpot(0, c);
        manageNewSpot(h - 1, c);
    }

    while (!q.empty()) {
        auto [r, c] = q.front();
        q.pop();

        for (auto &[dr, dc] : d) {
            int nr = r + dr;
            int nc = c + dc;
            if (1 <= nr && nr < h - 1 && 1 <= nc && nc < w - 1 && !visited[nr][nc]) {
                manageNewSpot(nr, nc);        
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    vector<int> vec;
    int T; cin >> T;
    for (int i = 0; i < T; i++) {
        ans = 0;
        solution();
        vec.push_back(ans);
    }
    for (int &e : vec) {
        cout << e << '\n';
    }
    return 0;
}