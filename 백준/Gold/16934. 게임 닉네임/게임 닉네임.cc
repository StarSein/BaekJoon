#include <iostream>
#include <cstring>
#include <string>
#include <unordered_map>
using namespace std;
#define NUM_CHAR 26

struct Trie {
    Trie* child[NUM_CHAR];

    Trie() {
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
unordered_map<string, int> table;

string generate(string s) {
    Trie* cur = root;
    int pos = -1;
    for (int i = 0; i < s.size(); i++) {
        int index = s[i] - 'a';
        if (cur->child[index] == 0) {
            if (pos == -1) {
                pos = i;
            }
            cur->child[index] = new Trie();
        }
        cur = cur->child[index];
    }

    if (table.count(s)) {
        table[s]++;
    } else {
        table.emplace(s, 1);
    }

    if (pos == -1) {
        string suffix = to_string(table[s]);
        return s + (suffix == "1" ? "" : suffix);
    } else {
        return s.substr(0, pos + 1);
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        string s; cin >> s;
        string nickname = generate(s);
        cout << nickname << '\n';
    }
    return 0;
}