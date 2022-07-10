#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <iterator>
using namespace std;

const int NUM_CHAR = 62;
vector<int> table;
map<char, int> ctoi;
vector<int> ans;

void makeTable(string &p) {
    table.resize(p.size(), 0);
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

int kmp(string &s, string &p) {
    int ret = 0;
    int j = 0;
    for (int i = 0; i < s.size(); i++) {
        while (j && s[i] != p[j]) {
            j = table[j-1];
        }
        if (s[i] == p[j]) {
            if (j == p.size()-1) {
                ret++;
                j = table[j];
            } else {
                ++j;
            }
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    while (n--) {
        ctoi.clear();
        string a, w, s; cin >> a >> w >> s;
        for (int i = 0; i < a.size()-1; i++) {
            ctoi.emplace(a[i], i+1);
        }
        ctoi.emplace(a[a.size()-1], 0);

        ans.clear();
        for (int i = 0; i < a.size(); i++) {
            makeTable(w);
            if (kmp(s, w) == 1) {
                ans.push_back(i);
            }

            for (int j = 0; j < w.size(); j++) {
                w[j] = a[ctoi[w[j]]];
            }
        }
        if (ans.size() == 0) {
            cout << "no solution" << '\n';
        } else if (ans.size() == 1) {
            cout << "unique: " << ans[0] << '\n';
        } else {
            cout << "ambiguous: ";
            copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
            cout << '\n';
        }
    }

}