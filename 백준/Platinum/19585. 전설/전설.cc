#include <iostream>
#include <cstring>
#include <unordered_set>
using namespace std;
#define NUM_CHAR 26

struct Trie {
    bool term;
    Trie* child[NUM_CHAR];

    Trie(): term(false) {
        memset(child, 0, sizeof(child));
    }
    ~Trie() {
        for (int i = 0; i < NUM_CHAR; i++) {
            if (child[i]) {
                delete child[i];
            }
        }
    }
};

Trie* root = new Trie();
unordered_set<string> table;

void insert(string s) {
    Trie* cur = root;
    for (char &c : s) {
        int index = c - 'a';
        if (cur->child[index] == 0) {
            cur->child[index] = new Trie();
        }
        cur = cur->child[index];
    }
    cur->term = true;
}

bool check(string s) {
    Trie* cur = root;
    for (int i = 0; i < s.size(); i++) {
        int index = s[i] - 'a';
        if (cur->child[index] == 0) {
            return false;
        } else {
            cur = cur->child[index];
            if (cur->term) {
                if (table.count(s.substr(i + 1))) {
                    return true;
                }
            }
        }
    }
    return false;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int C, N; cin >> C >> N;
    for (int i = 0; i < C; i++) {
        string color; cin >> color;
        insert(color);
    }
    for (int i = 0; i < N; i++) {
        string nickname; cin >> nickname;
        table.insert(nickname);
    }
    int Q; cin >> Q;
    for (int i = 0; i < Q; i++) {
        string name; cin >> name;
        cout << (check(name) ? "Yes" : "No") << '\n';
    }
    return 0;
}