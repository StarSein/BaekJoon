#include <iostream>
using namespace std;

const int MAX_N = 1e5 + 1, MAX_K = 1e5 + 1, LOG = 31;
int video[MAX_N], table[MAX_K][LOG];


int getVideo(int n, int x) {
    int exp = LOG - 1;
    int bit = 1 << exp;
    while (bit) {
        if (bit & n) {
            x = table[x][exp];
        }
        exp--;
        bit >>= 1;
    }
    return x;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N, K, M; cin >> N >> K >> M;
    for (int i = 1; i <= N; i++) {
        cin >> video[i];
    }
    for (int i = 1; i <= K; i++) {
        cin >> table[i][0];
    }
    for (int exp = 1; exp < LOG; exp++) {
        for (int i = 1; i <= K; i++) {
            int x = table[i][exp-1];
            table[i][exp] = table[x][exp-1];
        }
    }
    for (int i = 1; i <= N; i++) {
        cout << getVideo(M - 1, video[i]) << ' ';
    }
    cout << '\n';
    return 0;
}