#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

/*
1. 어떤 노드의 자식 노드의 수가 k개일 때 각 자식 노드의 자식 수는 0, 1, 2, ... , k-1개
2. 1을 위배하게 만드는, 서로 자식 수가 같은 두 자식에 대해, 1이 성립하지 않는 것에 합친다.
3. 두 자식 모두 1이 성립한다면, 두 자식 모두에 합칠 수 있다.

1. 루트 노드의 자식 수가 같으면 루트 노드끼리 합치는 두 가지 경우가 답이다.
2. 루트 노드의 자식 수가 많은 쪽에 적은 쪽이 자식으로 합쳐지게 된다.
*/

int N;
int mainR, subR;
vector<vector<int>> child;
vector<bool> isChild;
vector<int> root;
vector<pi> ans;

bool isComplete(int node) {
    int curBit = 0, stdBit = (1 << (int)child[node].size()) - 1;
    for (int &childNode : child[node]) {
        curBit |= 1 << (int)child[childNode].size();
    }
    return (curBit == stdBit);
}

void dfs(int node) {
    if (isComplete(node)) {
        for (int &childNode : child[node]) {
            dfs(childNode);
        }
    } else {
        int numChild = child[node].size();
        int cnt[numChild+1];
        memset(cnt, 0, sizeof(cnt));
        for (int &childNode : child[node]) {
            cnt[child[childNode].size()]++;
        }
        int target = -1;
        for (int i = 0; i <= numChild; i++) {
            if (cnt[i] == 2) {
                target = i;
                break;
            }
        }
        vector<int> candid;
        for (int &childNode : child[node]) {
            if (child[childNode].size() == target) {
                candid.push_back(childNode);
            }
        }
        vector<int> cpl, ucpl;
        for (int &cdd : candid) {
            if (isComplete(cdd)) {
                cpl.push_back(cdd);
            } else {
                ucpl.push_back(cdd);
            }
        }
        if (cpl.size() == 2) {
            for (int &e : cpl) {
                ans.emplace_back(e, subR);
            }
        } else {
            ans.emplace_back(ucpl[0], subR);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    int SZ = 1 << N;
    child.resize(SZ+1);
    isChild.resize(SZ+1);
    for (int i = 0; i < SZ - 2; i++) {
        int a, b; cin >> a >> b;
        child[a].push_back(b);
        isChild[b] = true;
    }
    for (int i = 1; i <= SZ; i++) {
        if (!isChild[i]) {
            root.push_back(i);
        }
    }
    if (child[root[0]].size() >= child[root[1]].size()) {
        mainR = root[0], subR = root[1];
    } else {
        mainR = root[1], subR = root[0];
    }
    if (child[mainR].size() == child[subR].size()) {
        ans.emplace_back(mainR, subR);
        ans.emplace_back(subR, mainR);
    } else if (child[mainR].size() != N) {
        ans.emplace_back(mainR, subR);
    } else {
        dfs(mainR);
    }
    sort(ans.begin(), ans.end());
    for (auto &[win, lose] : ans) {
        cout << win << ' ' << lose << '\n';
    }
}