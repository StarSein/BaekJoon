#include <iostream>
using namespace std;

const int MAX_N = 5, INF = 1'000'000;
int n;
char grid[MAX_N][MAX_N];
int maxDP[MAX_N][MAX_N];
int minDP[MAX_N][MAX_N];

int dy[] = {0, -1, -1, -2};
int dx[] = {-2, -1, -1, 0};
int oy[] = {0, 0, -1, -1};
int ox[] = {-1, -1, 0, 0};

int getMax(int r, int c);
int getMin(int r, int c);


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> grid[i][j];

    fill(&maxDP[0][0], &maxDP[0][0] + MAX_N * MAX_N, INF);
    fill(&minDP[0][0], &minDP[0][0] + MAX_N * MAX_N, INF);
    maxDP[0][0] = minDP[0][0] = grid[0][0] - '0';
    cout << getMax(n-1, n-1) << ' ' << getMin(n-1, n-1) << '\n';
}

int getMax(int r, int c) {
    if (maxDP[r][c] != INF)
        return maxDP[r][c];
    
    int maxVal = -INF;
    int curNum = grid[r][c] - '0';

    int nr, nc;
    int pr, pc;
    for (int i = 0; i < 4; i++) {
        nr = r + dy[i]; nc = c + dx[i];
        if (0 <= nr && nr < n && 0 <= nc && nc < n) {
            pr = r + oy[i]; pc = c + ox[i];
            if (grid[pr][pc] == '+') {
                maxVal = max(maxVal, getMax(nr, nc) + curNum);
            } else if (grid[pr][pc] == '-') {
                maxVal = max(maxVal, getMax(nr, nc) - curNum);
            } else {
                maxVal = max(maxVal, getMax(nr, nc) * curNum);
                maxVal = max(maxVal, getMin(nr, nc) * curNum);
            }
        }
    }
    return maxDP[r][c] = maxVal;
}

int getMin(int r, int c) {
    if (minDP[r][c] != INF)
        return minDP[r][c];

    int minVal = INF;
    int curNum = grid[r][c] - '0';

    int nr, nc;
    int pr, pc;
    for (int i = 0; i < 4; i++) {
        nr = r + dy[i]; nc = c + dx[i];
        if (0 <= nr && nr < n && 0 <= nc && nc < n) {
            pr = r + oy[i]; pc = c + ox[i];
            if (grid[pr][pc] == '+') {
                minVal = min(minVal, getMin(nr, nc) + curNum);
            } else if (grid[pr][pc] == '-') {
                minVal = min(minVal, getMin(nr, nc) - curNum);
            } else {
                minVal = min(minVal, getMax(nr, nc) * curNum);
                minVal = min(minVal, getMin(nr, nc) * curNum);
            }
        }
    }
    return minDP[r][c] = minVal;
}