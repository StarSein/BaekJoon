#include <iostream>
#include <vector>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 10;
const int MAX_H = 30;
const int INF = 4;
bool isLeft[MAX_N + 1][MAX_H + 1];
bool isRight[MAX_N + 1][MAX_H + 1];
vector<pi> vec;

int N, M, H; 
int ans = INF;


bool simulate() {
    for (int i = 1; i <= N; i++) {
        int pos = i;
        for (int j = 1; j <= H; j++) {
            if (isLeft[pos][j]) {
                pos--;
            } else if (isRight[pos][j]) {
                pos++;
            }
        }
        if (pos != i) {
            return false;
        }
    }
    return true;
}

void dfs(int cnt, int pos) {
    if (cnt == INF) {
        return;
    }

    if (simulate()) {
        ans = min(ans, cnt);
        return;
    }

    for (int i = pos; i < vec.size(); i++) {
        auto &[b, a] = vec[i];
        isRight[b][a] = true;
        isLeft[b + 1][a] = true;
        dfs(cnt + 1, i + 1);
        isRight[b][a] = false;
        isLeft[b + 1][a] = false;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M >> H;
    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        isRight[b][a] = true;
        isLeft[b + 1][a] = true;
    }

    vec.reserve((N - 1) * H - M);
    for (int i = 1; i < N; i++) {
        for (int j = 1; j <= H; j++) {
            if (!isRight[i][j] && !isLeft[i + 1][j]) {
                vec.emplace_back(i, j);
            }
        }
    }
    dfs(0, 0);
    cout << (ans != INF ? ans : -1);
    return 0;
}