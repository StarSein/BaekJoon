#include <iostream>
#include <unordered_set>
#include <queue>
using namespace std;

const int SZ = 3;
const string target = "123456780";
char grid[SZ][SZ];

queue<string> q;
unordered_set<string> visited;

int dy[] {0, 1, 0, -1};
int dx[] {1, 0, -1, 0};

string makeStr() {
    string s;
    s.reserve(SZ * SZ);
    for (int i = 0; i < SZ; i++) {
        for (int j = 0; j < SZ; j++) {
            s.push_back(grid[i][j]);
        }
    }
    return s;
}

void updateGrid(string &s) {
    for (int i = 0; i < SZ; i++) {
        for (int j = 0; j < SZ; j++) {
            grid[i][j] = s[3 * i + j];
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < SZ; i++) {
        for (int j = 0; j < SZ; j++) {
            cin >> grid[i][j];
        }
    }

    q.push(makeStr());
    int ans = 0;
    while (!q.empty()) {
        int sizeQ = q.size();
        while (sizeQ--) {
            string &curStr = q.front();
            q.pop();

            visited.insert(curStr);

            if (curStr == target) {
                cout << ans;
                return 0;
            }

            updateGrid(curStr);
            for (int r = 0; r < SZ; r++) {
                for (int c = 0; c < SZ; c++) {
                    if (grid[r][c] == '0') {
                        for (int i = 0; i < 4; i++) {
                            int nr = r + dy[i], nc = c + dx[i];
                            if (0 <= nr && nr < SZ && 0 <= nc && nc < SZ) {
                                swap(grid[r][c], grid[nr][nc]);
                                string nextStr = makeStr();
                                if (!visited.count(nextStr)) {
                                    visited.insert(nextStr);
                                    q.push(nextStr);
                                }
                                swap(grid[r][c], grid[nr][nc]);
                            }
                        }
                    }
                }
            }
        }

        ans++;
    }
    cout << -1;
    return 0;
}