#include <iostream>
#include <vector>
using namespace std;

int N, M, K;
vector<string> board;
vector<vector<int>> mis;

int getAreaSum(int r, int c) {
    int sum1 = mis[r + K - 1][c + K - 1] + mis[r - 1][c - 1]
                - mis[r + K - 1][c - 1] - mis[r - 1][c + K - 1];
    return min(sum1, K * K - sum1);
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M >> K;
    board.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> board[i];
    }

    mis.resize(N + 1, vector<int>(M + 1));
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < M; c++) {
            if ((r + c) & 1) {
                if (board[r][c] == 'B') {
                    mis[r + 1][c + 1] = 1;
                }
            } else {
                if (board[r][c] == 'W') {
                    mis[r + 1][c + 1] = 1;
                }
            }
        }
    }

    for (int r = 1; r <= N; r++) {
        for (int c = 1; c < M; c++) {
            mis[r][c + 1] += mis[r][c];
        }
    }

    for (int c = 1; c <= M; c++) {
        for (int r = 1; r < N; r++) {
            mis[r + 1][c] += mis[r][c];
        }
    }

    int ans = N * M;
    for (int r = 1; r + K - 1 <= N; r++) {
        for (int c = 1; c + K - 1 <= M; c++) {
            ans = min(ans, getAreaSum(r, c));
        }
    }
    cout << ans;
    return 0;
}