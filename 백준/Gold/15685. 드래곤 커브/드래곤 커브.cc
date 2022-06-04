#include <iostream>
#include <vector>
using namespace std;


int n;
const int MAX_X = 100;
vector<vector<bool>> grid(MAX_X+1, vector<bool>(MAX_X+1, false));

vector<int> dy {0, -1, 0, 1};
vector<int> dx {1, 0, -1, 0};

void drawCurve(int x, int y, int d, int g) {
    grid[y][x] = true;
    y += dy[d];
    x += dx[d];
    grid[y][x] = true;
    vector<int> vDir;
    vDir.reserve(1 << g);
    vDir.push_back(d);
    for (int i = 0; i < g; i++) {
        for (int j = vDir.size() - 1; j != -1; j--) {
            int curD = (vDir[j] + 1) % 4;
            y += dy[curD];
            x += dx[curD];
            grid[y][x] = true;
            vDir.push_back(curD);
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, y, d, g; cin >> x >> y >> d >> g;
        drawCurve(x, y, d, g);
    }

    int answer = 0;
    for (int i = 0; i < MAX_X; i++) {
        for (int j = 0; j < MAX_X; j++) {
            if (grid[i][j] && grid[i+1][j] && grid[i][j+1] && grid[i+1][j+1])
                answer++;
        }
    }
    cout << answer;
}