#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <algorithm>
#include <iterator>
using namespace std;
typedef pair<int, int> pi;

vector<int> vNumPreceder, vTime, vAnswer;
vector<vector<int>> vFollowers;
priority_queue<pi, vector<pi>, greater<pi>> pq;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    vNumPreceder.resize(n + 1);
    vTime.resize(n + 1);
    vAnswer.resize(n + 1);
    vFollowers.resize(n + 1, vector<int>());
    int preceder;
    for (int i = 1; i <= n; i++) {
        cin >> vTime[i] >> preceder;
        while (preceder != -1) {
            vNumPreceder[i]++;
            vFollowers[preceder].push_back(i);
            cin >> preceder;
        }
    }
    for (int i = 1; i <= n; i++) {
        if (vNumPreceder[i] == 0)
            pq.emplace(vTime[i], i);
    }
    int curTime, curNode;
    while (!pq.empty()) {
        tie(curTime, curNode) = pq.top();
        pq.pop();
        vAnswer[curNode] = curTime;
        for (int& follower : vFollowers[curNode]) {
            if (vNumPreceder[follower] == 1) {
                pq.emplace(curTime + vTime[follower], follower);
            } else {
                vNumPreceder[follower]--;
            }
        }
    }
    copy(next(vAnswer.begin(), 1), vAnswer.end(), ostream_iterator<int>(cout, "\n"));
}