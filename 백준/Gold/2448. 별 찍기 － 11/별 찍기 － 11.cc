#include <iostream>
#include <cmath>
using namespace std;

const int MAX_R = 3072, MAX_C = 6144;
char grid[MAX_R][MAX_C];
int n, k;

void drawPoint(int depth, int r, int c) {
    if (depth > k) {
        return;
    }
    grid[r][c] = '*';
    grid[r+1][c-1] = grid[r+1][c+1] = '*';
    for (int i = -2; i <= 2; i++) {
        grid[r+2][c+i] = '*';
    }
    int step = 3*pow(2, depth);
    drawPoint(depth+1, r, c);
    drawPoint(depth+1, r+step, c-step);
    drawPoint(depth+1, r+step, c+step);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    k = (int) log2(n/3);
    fill(&grid[0][0], &grid[0][0] + MAX_R * MAX_C, ' ');
    drawPoint(0, 0, 3*pow(2, k)-1);
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < 2*n; c++) {
            cout << grid[r][c];
        }
        cout << '\n';
    }
}