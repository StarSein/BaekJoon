#include <iostream>
#include <map>
#include <queue>
using namespace std;
#define X first
#define Y second
typedef pair<int, int> pi;

int n, t;
map<pi, bool> visit;

int bfs(int x, int y) {
    int cnt = 0;
    queue<pi> cq, nq;
    nq.emplace(x, y);
    pi cur, next;
    while (!nq.empty()) {
        swap(cq, nq);
        while (!cq.empty()) {
            cur = cq.front();
            cq.pop();
            if (cur.Y == t)
                return cnt;
            for (int dx = -2; dx < 3; dx++) {
                for (int dy = -2; dy < 3; dy++) {
                    next = {cur.X + dx, cur.Y + dy};
                    if (visit.find(next) != visit.end() && !visit[next]) {
                        visit[next] = true;
                        nq.push(next);
                    }
                }
            }
        }
        cnt++;
    }
    return -1;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> t;
    int x, y;
    for (int i = 0; i < n; i++) {
        cin >> x >> y;
        visit.insert({{x, y}, false});
    }
    cout << bfs(0, 0);
}