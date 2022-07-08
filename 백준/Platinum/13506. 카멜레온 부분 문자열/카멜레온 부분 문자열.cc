#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> table;

void makeTable(string &p) {
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

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string p; cin >> p;
    table.resize(p.size(), 0);
    makeTable(p);
    int sufLen = table[p.size()-1];
    if (sufLen) {
        if (find(table.begin(), prev(table.end(), 1), sufLen) == prev(table.end(), 1)) {
            string subp = p.substr(0, sufLen);
            table.clear();
            makeTable(subp);
            sufLen = table[subp.size()-1];
        }
    }
    cout << (sufLen ? p.substr(0, sufLen) : "-1");
}