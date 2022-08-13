#include <iostream>
#include <queue>
using namespace std;
typedef long long ll;
const int MAX_LEN = 20;

queue<int> arrQIn[MAX_LEN + 1], arrQOut[MAX_LEN + 1];

ll ans = 0;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K; cin >> N >> K;
    for (int i = 0; i < N; i++) {
        string name; cin >> name;

        arrQIn[name.size()].push(i);
    }

    for (int len = 1; len <= MAX_LEN; len++) {
        while (!arrQIn[len].empty()) {
            int curRank = arrQIn[len].front();
            arrQIn[len].pop();
            while (!arrQOut[len].empty() && arrQOut[len].front() < curRank - K) {
                arrQOut[len].pop();
                ans += arrQOut[len].size();
            }
            arrQOut[len].push(curRank);
        }
        while (!arrQOut[len].empty()) {
            arrQOut[len].pop();
            ans += arrQOut[len].size();
        }
    }
    cout << ans;
    return 0;
}