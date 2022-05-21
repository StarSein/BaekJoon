#include <bits/stdc++.h>
using namespace std;

stack<vector<int>> stGroup, stRev;
vector<int> finalInfo, curInfo, initInfo;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    int n, m; cin >> n >> m;
    vector<int> curGroup;
    for (int i = 0; i < m; i++) {
        int k; cin >> k;
        curGroup.resize(k, 0);
        for (int j = 0; j < k; j++)
            cin >> curGroup[j];
        stGroup.push(curGroup);
    }
    finalInfo.resize(n + 1, 0);
    curInfo.resize(n + 1, 0);
    initInfo.resize(n + 1, 0);
    for (int i = 1; i <= n; i++)
        cin >> finalInfo[i];
    
    copy(finalInfo.begin(), finalInfo.end(), curInfo.begin());
    while (!stGroup.empty()) {
        curGroup = stGroup.top();
        stGroup.pop();
        bool isInfested = true;
        for (int& e : curGroup) {
            if (!curInfo[e]) {
                isInfested = false;
                break;
            }
        }
        if (!isInfested)
            for (int& e : curGroup)
                curInfo[e] = 0;
        stRev.push(curGroup);
    }
    copy(curInfo.begin(), curInfo.end(), initInfo.begin());
    while (!stRev.empty()) {
        curGroup = stRev.top();
        stRev.pop();
        bool isInfested = false;
        for (int& e : curGroup) {
            if (curInfo[e]) {
                isInfested = true;
                break;
            }
        }
        if (isInfested)
            for (int& e : curGroup)
                curInfo[e] = 1;
    }
    
    if (curInfo == finalInfo) {
        cout << "YES" << '\n';
        for (int i = 1; i <= n; i++)
            cout << initInfo[i] << ' ';
    } else {
        cout << "NO";
    }
}