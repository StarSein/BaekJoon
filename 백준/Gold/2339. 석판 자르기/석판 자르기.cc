#include <iostream>
using namespace std;

const int MAX_N = 20;

char grid[MAX_N][MAX_N];
int rowSum1[MAX_N+1][MAX_N+1], rowSum2[MAX_N+1][MAX_N+1],
    colSum1[MAX_N+1][MAX_N+1], colSum2[MAX_N+1][MAX_N+1];


int getNum1(int r1, int c1, int r2, int c2) {
    int ret = 0;
    for (int r = r1; r <= r2; r++) {
        ret += rowSum1[r][c2] - rowSum1[r][c1-1];
    }
    return ret;
}

int getNum2(int r1, int c1, int r2, int c2) {
    int ret = 0;
    for (int r = r1; r <= r2; r++) {
        ret += rowSum2[r][c2] - rowSum2[r][c1-1];
    }
    return ret;
}

int getNumCase(int r1, int c1, int r2, int c2, bool isHor) {
    int num1 = getNum1(r1, c1, r2, c2);
    int num2 = getNum2(r1, c1, r2, c2);
    if (num1 != num2 - 1) {
        return 0;
    }
    if (num2 == 1) {
        return 1;
    }
    int ret = 0;
    if (isHor) {
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                if (grid[r-1][c-1] == '1' && rowSum1[r][c2] - rowSum1[r][c1-1] == 1 && rowSum2[r][c2] - rowSum2[r][c1-1] == 0) {
                    ret += getNumCase(r1, c1, r-1, c2, !isHor) * getNumCase(r+1, c1, r2, c2, !isHor);
                }
            }
        }
    } else {
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                if (grid[r-1][c-1] == '1' && colSum1[r2][c] - colSum1[r1-1][c] == 1 && colSum2[r2][c] - colSum2[r1-1][c] == 0) {
                    ret += getNumCase(r1, c1, r2, c-1, !isHor) * getNumCase(r1, c+1, r2, c2, !isHor);
                }  
            }
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int N; cin >> N;
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            cin >> grid[r][c];
        }
    }

    for (int r = 1; r <= N; r++) {
        for (int c = 1; c <= N; c++) {
            rowSum1[r][c] = rowSum1[r][c-1];
            rowSum2[r][c] = rowSum2[r][c-1];
            if (grid[r-1][c-1] == '1') {
                rowSum1[r][c]++;
            } else if (grid[r-1][c-1] == '2') {
                rowSum2[r][c]++;
            }
        }
    }
    for (int c = 1; c <= N; c++) {
        for (int r = 1; r <= N; r++) { 
            colSum1[r][c] = colSum1[r-1][c];
            colSum2[r][c] = colSum2[r-1][c];
            if (grid[r-1][c-1] == '1') {
                colSum1[r][c]++;
            } else if (grid[r-1][c-1] == '2') {
                colSum2[r][c]++;
            }
        }
    }

    int num = getNumCase(1, 1, N, N, true) + getNumCase(1, 1, N, N, false);
    cout << (num ? num : -1);
    return 0;
}