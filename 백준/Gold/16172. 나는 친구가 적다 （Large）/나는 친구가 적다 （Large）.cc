#include <iostream>
#include <vector>
using namespace std;

const int MAX_N = 2e5;
vector<int> table;
vector<char> s;

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

bool kmp(vector<char> &s, string &p) {
    int j = 0;
    for (int i = 0; i < s.size(); i++) {
        while (j && s[i] != p[j]) {
            j = table[j-1];
        }
        if (s[i] == p[j]) {
            if (j == p.size() - 1) {
                return true;
            } else {
                ++j;
            }
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string strS, strP; cin >> strS >> strP;
    table.resize(strP.size(), 0);
    s.reserve(strS.size());
    for (char &c : strS) {
        if ('A' <= c && c <= 'z') {
            s.push_back(c);
        }
    }
    makeTable(strP);
    cout << kmp(s, strP);
}