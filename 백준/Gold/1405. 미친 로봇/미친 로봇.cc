#include <iostream>
using namespace std;

const int SZ = 30;
int n;
double p[4];
bool visit[SZ][SZ];

int dy[] {0, 0, 1, -1};
int dx[] {1, -1, 0, 0};


double dfs(int r, int c, int depth) {
    if (visit[r][c]) {
        return 0;
    }
    if (depth == n) {
        return 1;
    }
    visit[r][c] = true;
    double ret = 0;
    for (int i = 0; i < 4; i++) {
        ret += p[i] * dfs(r + dy[i], c + dx[i], depth + 1);
    }
    visit[r][c] = false;
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    for (int i = 0; i < 4; i++) {
        cin >> p[i];
        p[i] /= 100;
    }

    cout.precision(16);
    cout << dfs(15, 15, 0);
    return 0;
}