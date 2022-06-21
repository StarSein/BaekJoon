#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
#include <iterator>
using namespace std;
typedef pair<int, int> pi;


int n, m, k;
vector<pi> vEdge;
vector<int> vRoot, vScore;

void initRoot() {
    for (int i = 1; i <= n; i++) {
        vRoot[i] = i;
    }
}

int find(int x) {
    if (vRoot[x] == x) {
        return x;
    }
    return vRoot[x] = find(vRoot[x]);
}

void merge(int a, int b) {
    if (a > b) {
        swap(a, b);
    }
    vRoot[b] = a;
}

int getScore(int pos) {
    int x, y, rx, ry, ret = 0;
    for (int i = pos; i < vEdge.size(); i++) {
        tie(x, y) = vEdge[i];
        rx = find(x);
        ry = find(y);
        if (rx != ry) {
            merge(rx, ry);
            ret += i + 1;
        }
    }
    for (int i = 1; i <= n; i++) {
        if (find(i) != 1) {
            return 0;
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m >> k;
    vRoot.resize(n + 1);
    vEdge.reserve(m);
    int x, y;
    for (int i = 0; i < m; i++) {
        cin >> x >> y;
        vEdge.emplace_back(x, y);
    }
    
    vScore.resize(k, 0);
    int score;
    for (int i = 0; i < k; i++) {
        initRoot();
        score = getScore(i);
        if (score) {
            vScore[i] = score;
        }
        else {
            break;
        }
    }
    copy(vScore.begin(), vScore.end(), ostream_iterator<int>(cout, " "));
}