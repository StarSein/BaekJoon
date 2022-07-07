#include <iostream>
#include <queue>
#include <tuple>
#include <vector>
using namespace std;
typedef pair<int, int> pi;
queue<pi> q;

const int MAX_F = 1e6+1;
bool visit[MAX_F];
vector<int> step;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int f, s, g, u, d; cin >> f >> s >> g >> u >> d;

    step.push_back(u);
    step.push_back(-d);
    q.emplace(0, s);
    visit[s] = true;


    int dist, cur, nex;
    while (!q.empty()) {
        tie(dist, cur) = q.front();
        q.pop();
        if (cur == g) {
            cout << dist;
            return 0;
        }
        for (int &e : step) {
            nex = cur + e;
            if (1 <= nex && nex <= f && !visit[nex]) {
                q.emplace(dist + 1, nex);
                visit[nex] = true;
            }
        }
    }
    cout << "use the stairs";
    return 0;
}