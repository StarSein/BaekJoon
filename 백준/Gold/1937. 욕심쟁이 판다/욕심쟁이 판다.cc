#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;


int n;
vector<vector<int>> amount, cache;

int dy[4] = {0, 1, 0, -1};
int dx[4] = {1, 0, -1, 0};

int dfs(int row, int col) {
    if (cache[row][col] != -1)
        return cache[row][col];
    int maxMove = 1;
    int nr = 0, nc = 0;
    for (int i = 0; i < 4; i++) {
        nr = row + dy[i], nc = col + dx[i];
        if (0 <= nr && nr < n && 0 <= nc && nc < n && amount[row][col] < amount[nr][nc]) {
            maxMove = max(maxMove, 1 + dfs(nr, nc));
        }
    }
    return cache[row][col] = maxMove;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> n;
    amount.resize(n, vector<int>(n, 0));
    cache.resize(n, vector<int>(n, -1));
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> amount[i][j];

    int answer = 0;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            answer = max(answer, dfs(i, j));
    cout << answer;
    return 0;
}