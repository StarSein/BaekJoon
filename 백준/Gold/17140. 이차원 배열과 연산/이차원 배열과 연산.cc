#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

const int SIZE = 100;
int arr[SIZE][SIZE];
int cnt[SIZE+1], num[SIZE];
int row = 3, col = 3;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int r, c, k; cin >> r >> c >> k; r--; c--;
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            cin >> arr[i][j];
        }
    }

    int time = 0;
    while (time <= 100) {
        if (arr[r][c] == k) {
            cout << time;
            return 0;
        }
        time++;
        if (row >= col) {
            int nc = 0;
            for (int i = 0; i < row; i++) {
                memset(num, 0, sizeof(num));
                memset(cnt, 0, sizeof(cnt));
                for (int j = 0; j < col; j++) {
                    if (arr[i][j]) {
                        cnt[arr[i][j]]++;
                    }
                }
                int cc = 0;
                for (int j = 1; j <= SIZE; j++) {
                    if (cnt[j]) {
                        num[cc++] = j;
                    }
                }
                sort(num, num + cc, [](int &a, int &b)->bool{return cnt[a] == cnt[b] ? a < b : cnt[a] < cnt[b];});
                nc = max(min(2*cc, SIZE), nc);
                int x = 0;
                for (int j = 0; j < SIZE / 2; j++) {
                    arr[i][x++] = num[j];
                    arr[i][x++] = cnt[num[j]];
                }
            }
            col = nc;
        } else {
            int nr = 0;
            for (int i = 0; i < col; i++) {
                memset(num, 0, sizeof(num));
                memset(cnt, 0, sizeof(cnt));
                for (int j = 0; j < row; j++) {
                    if (arr[j][i]) {
                        cnt[arr[j][i]]++;
                    }
                }
                int cr = 0;
                for (int j = 1; j <= SIZE; j++) {
                    if (cnt[j]) {
                        num[cr++] = j;
                    }
                }
                sort(num, num + cr, [](int &a, int &b)->bool{return cnt[a] == cnt[b] ? a < b : cnt[a] < cnt[b];});
                nr = max(min(SIZE, 2*cr), nr);
                int y = 0;
                for (int j = 0; j < SIZE / 2; j++) {
                    arr[y++][i] = num[j];
                    arr[y++][i] = cnt[num[j]];
                }
            }
            row = nr;
        }
    }
    cout << -1;
    return 0;
}