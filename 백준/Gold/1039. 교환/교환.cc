#include <iostream>
#include <cstring>
#include <queue>
using namespace std;

const int MAX_N = 1e6;
bool visit[MAX_N + 1];
queue<int> q;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K; cin >> N >> K;

    q.push(N);
    while (K--) {
        int sizeQ = q.size();

        memset(visit, 0, sizeof(visit));
        while (sizeQ--) {
            string curStr = to_string(q.front());
            q.pop();

            for (int i = 0; i < curStr.size() - 1; i++) {
                for (int j = i + 1; j < curStr.size(); j++) {
                    if (i || curStr[j] != '0') {
                        swap(curStr[i], curStr[j]);
                        int curN = stoi(curStr);
                        if (!visit[curN]) {
                            visit[curN] = true;
                            q.push(curN);
                        }
                        swap(curStr[i], curStr[j]);
                    }
                }
            }
        }
    }
    int ans = -1;
    while (!q.empty()) {
        ans = max(ans, q.front());
        q.pop();
    }
    cout << ans;
    return 0;
}