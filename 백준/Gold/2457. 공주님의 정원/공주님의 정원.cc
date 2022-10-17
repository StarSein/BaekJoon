#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;


const int LB = 301, RB = 1130;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    vector<pi> vPeriod(n);
    int sm, sd, em, ed;
    int startPos, endPos;
    for (int i = 0; i < n; i++) {
        cin >> sm >> sd >> em >> ed;
        startPos = sm * 100 + sd;
        endPos = em * 100 + ed;
        vPeriod[i] = {startPos, endPos};
    }
    sort(vPeriod.begin(), vPeriod.end(), [](pi& a, pi& b) {return a.first < b.first;});

    int curDL = LB, nextDL = LB, cnt = 1;
    int i = 0, answer = 0;
    while (i < vPeriod.size()) {
        if (vPeriod[i].first <= curDL)
            nextDL = max(nextDL, vPeriod[i].second);
        else {
            curDL = nextDL;
            cnt++;
            if (vPeriod[i].first > curDL)
                break;
            else
                nextDL = max(nextDL, vPeriod[i].second);
        }
        if (nextDL > RB) {
            answer = cnt;
            break;
        }
        i++;
    }
    cout << answer;
}