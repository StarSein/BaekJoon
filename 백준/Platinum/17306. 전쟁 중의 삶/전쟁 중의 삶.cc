#include <iostream>
#include <vector>
#include <cmath>
#include <cstring>
using namespace std;
typedef unsigned long long ull;

vector<const char *> vBin;
ull answer = 0;

inline int charToIdx(char c) {
    return c - '0';
}

struct Trie {
public:
    Trie *child[2];
    bool isDanger;

    Trie() : isDanger(false) {
        memset(child, 0, sizeof(child));
    }
    ~Trie() {
        for (int i = 0; i < 2; i++) {
            if (child[i]) {
                delete child[i];
            }
        }
    }

    void insert(const char *s) {
        if (*s == '\0') {
            isDanger = true;
            answer++;
        } else {
            int idx = charToIdx(*s);
            if (child[idx] == 0) {
                child[idx] = new Trie();
            }
            child[idx]->insert(s + 1);
        }
    }

    void search(const char *s, bool danger) {
        if (*s == '\0') {
            return;
        } else {
            if ((danger || !(child[0] == 0 || child[1] == 0)) && !isDanger) {
                isDanger = true;
                answer++;
            }
            int idx = charToIdx(*s);
            child[idx]->search(s + 1, isDanger);
        }
    }
}root;

char * toBinary(ull num) {
    string s;
    int pos = 50;
    while (~num & 1LL << pos) {
        pos--;
    }
    while (pos >= 0) {
        s += (num & 1LL << pos-- ? "1" : "0");
    }
    char * ret = new char[s.size()+1];
    copy(s.begin(), s.end(), ret);
    ret[s.size()] = '\0';
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    ull num;
    vBin.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> num;
        vBin[i] = toBinary(num);
    }

    for (auto &s : vBin) {
        root.insert(s);
    }

    for (auto &s : vBin) {
        root.search(s, false);
    }
    cout << answer;
}