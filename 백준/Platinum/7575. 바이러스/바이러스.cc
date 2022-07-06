#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<vector<int>> vCode, vRev;
vector<int> p, table;

void makeTable() {
    int j = 0;
    for (int i = 1; i < p.size(); i++) {
        while (j && p[i] != p[j]) {
            j = table[j-1];
        }
        if (p[i] == p[j]) {
            table[i] = ++j;
        }
    }
}

bool kmp(vector<int> &s) {
    int j = 0;
    for (int i = 0; i < s.size(); i++) {
        while (j && s[i] != p[j]) {
            j = table[j-1];
        }
        if (s[i] == p[j]) {
            if (j == p.size()-1) {
                return true;
            }
            ++j;
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k; cin >> n >> k;
    vCode.resize(n, vector<int>());
    vRev.resize(n, vector<int>());
    for (int i = 0; i < n; i++) {
        int m; cin >> m;
        vCode[i].resize(m, 0);
        vRev[i].resize(m, 0);
        for (int j = 0; j < m; j++) {
            cin >> vCode[i][j];
        }
        for (int j = 0; j < m; j++) {
            vRev[i][m-1-j] = vCode[i][j];
        }
    }
    
    p.reserve(vCode[0].size());
    table.resize(vCode[0].size(), 0);
    for (int pos = 0; pos <= vCode[0].size() - k; pos++) {
        p.clear();
        fill(table.begin(), table.end(), 0);
        copy(next(vCode[0].begin(), pos), next(vCode[0].begin(), pos + k), back_inserter(p));
        makeTable();
        bool flag = true;
        for (int i = 1; i < n; i++) {
            if (!kmp(vCode[i]) && !kmp(vRev[i])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            cout << "YES";
            return 0;
        }
    }
    cout << "NO";
    return 0;
}