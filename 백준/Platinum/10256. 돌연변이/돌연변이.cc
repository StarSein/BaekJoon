#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
#include <map>
using namespace std;
#define MAX_N 500'000
#define MAX_C 4

int ans;

map<char, int> trans;

inline int char_to_index(char &c) {
    return trans[c];
}

struct aho_corasick {
public:
    int trie[MAX_N][MAX_C], piv;
    int fail[MAX_N];
    bool term[MAX_N];

    void init(vector<string> &v) {
        memset(trie, 0, sizeof(trie));
        memset(fail, 0, sizeof(fail));
        memset(term, 0, sizeof(term));
        piv = 0;
        for (string &s : v) {
            int p = 0;
            for (char &c : s) {
                int i = char_to_index(c);
                if (!trie[p][i]) {
                    trie[p][i] = ++piv;
                }
                p = trie[p][i];
            }
            term[p] = true;
        }

        queue<int> q;
        for (int i = 0; i < MAX_C; i++) {
            if (trie[0][i]) {
                q.push(trie[0][i]);
            }
        }
        while (!q.empty()) {
            int x = q.front();
            q.pop();
            for (int i = 0; i < MAX_C; i++) {
                if (trie[x][i]) {
                    int p = fail[x];
                    while (p > 0 && !trie[p][i]) {
                        p = fail[p];
                    }
                    p = trie[p][i];
                    fail[trie[x][i]] = p;
                    if (term[p]) {
                        term[trie[x][i]] = true;
                    }
                    q.push(trie[x][i]);
                }
            }
        }
    }
    void count(string &s) {
        int p = 0;
        for (auto &c : s) {
            int i = char_to_index(c);
            while (p > 0 && !trie[p][i]) {
                p = fail[p];
            }
            p = trie[p][i];
            if (term[p]) {
                ans++;
            }
        }
    }
}aho;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    trans.emplace('A', 0);
    trans.emplace('C', 1);
    trans.emplace('G', 2);
    trans.emplace('T', 3);


    int t; cin >> t;
    while (t--) {
        int n, m; cin >> n >> m;
        string s, marker; cin >> s >> marker;
        vector<string> p;
        p.reserve(m * m);
        p.push_back(marker);
        string tmp;
        tmp.reserve(m);
        copy(marker.begin(), marker.end(), back_inserter(tmp));
        for (auto lit = tmp.begin(); lit != prev(tmp.end(), 1); lit++) {
            for (auto rit = next(lit, 1); rit != tmp.end(); rit++) {
                reverse(lit, next(rit, 1));
                p.push_back(tmp);
                tmp.clear();
                copy(marker.begin(), marker.end(), back_inserter(tmp));
            }
        }
        sort(p.begin(), p.end(), less<string>());
        p.erase(unique(p.begin(), p.end()), p.end());
        
        ans = 0;
        aho.init(p);
        aho.count(s);
        cout << ans << '\n';
    }
}