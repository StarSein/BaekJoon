#include <iostream>
#include <unordered_set>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

const int MAX_N = 8;
string init;
queue<string> q;
unordered_set<string> visited;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K; cin >> N >> K;
    init.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> init[i];
    }

    string target;
    for (int i = 1; i <= N; i++) {
        target.push_back('0' + i);
    }

    q.push(init);

    int cntFlip = 0;
    while (!q.empty()) {
        int qSize = q.size();
        while (qSize--) {
            string curS = q.front();
            q.pop();

            if (curS == target) {
                cout << cntFlip;
                return 0;
            }

            for (int i = 0; i <= N - K; i++) {
                string nextS = curS;
                reverse(next(nextS.begin(), i), next(nextS.begin(), i + K));
                if (!visited.count(nextS)) {
                    q.push(nextS);
                    visited.insert(nextS);
                }
            }
        }
        cntFlip++;
    }
    cout << -1;
    return 0;
}