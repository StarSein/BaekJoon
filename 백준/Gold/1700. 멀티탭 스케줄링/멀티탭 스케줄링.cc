#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<int> schedule;
vector<bool> isPlug;
vector<queue<int>> vqPos;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K; cin >> N >> K;
    schedule.reserve(K);
    isPlug.resize(K + 1, false);
    vqPos.resize(K + 1, queue<int>());

    for (int i = 0; i < K; i++) {
        int name; cin >> name;
        schedule.push_back(name);
        vqPos[name].push(i);
    }

    int cnt = 0, i = 0;
    while (cnt < N && i < K) {
        int &curName = schedule[i++];
        if (!isPlug[curName]) {
            isPlug[curName] = true;
            cnt++;
        }
    }

    int ans = 0;
    for (; i < K; i++) {
        int &curName = schedule[i];
        if (!isPlug[curName]) {
            int delName, delPos = -1;
            for (int j = 1; j <= K; j++) {
                if (isPlug[j]) {
                    queue<int> curQ = vqPos[j];
                    while (curQ.size() && curQ.front() <= i) {
                        curQ.pop();
                    }
                    if (curQ.empty()) {
                        delName = j;
                        break;
                    } else if (curQ.front() > delPos) {
                        delName = j;
                        delPos = curQ.front();
                    }
                }
            }
            isPlug[delName] = false;
            ans++;
            isPlug[curName] = true;
        }
    }
    cout << ans;
}