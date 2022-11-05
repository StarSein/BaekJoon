#include <iostream>
#include <cstring>
#include <vector>
using namespace std;
#define NUM_CHAR 63

struct Trie {
    bool rmAble;
    Trie* child[NUM_CHAR];
    Trie(): rmAble(false) {
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

Trie* root;
vector<string> rmVec;
int ans;

int charToIndex(char c) {
    if (c == '.') {
        return 0;
    } else if ('a' <= c && c <= 'z') {
        return c - 'a' + 1;
    } else if ('A' <= c && c <= 'Z') {
        return c - 'A' + 27;
    } else {
        return c - '0' + 53;
    }
}

void insert(string s, bool removable) {
    Trie* cur = root;
    cur->rmAble = removable;
    for (char &c : s) {
        int index = charToIndex(c);
        if (cur->child[index] == 0) {
            cur->child[index] = new Trie();
        }
        cur = cur->child[index];
        cur->rmAble = removable;
    }
}

void remove(string s) {
    Trie* cur = root;
    for (char &c : s) {
        int index = charToIndex(c);
        if (cur->child[index] == 0) {
            return;
        } else if (cur->child[index]->rmAble) {
            ans++;
            delete cur->child[index];
            cur->child[index] = 0;
            return;
        } else {
            cur = cur->child[index];
        }
    }
    ans++;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T; cin >> T;
    while (T--) {
        root = new Trie();
        int N1; cin >> N1;
        rmVec.reserve(N1);
        for (int i = 0; i < N1; i++) {
            string s; cin >> s;
            insert(s, true);
            rmVec.push_back(s);
        }
        int N2; cin >> N2;
        if (N2 == 0) {
            ans = 1;
        } else {
            ans = 0;
            for (int i = 0; i < N2; i++) {
                string s; cin >> s;
                insert(s, false);
            }

            for (string &s : rmVec) {
                remove(s);
            }    
        }


        delete root;
        rmVec.clear();

        cout << ans << '\n';
    }
    return 0;
}