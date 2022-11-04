#include <iostream>
#include <cstring>
using namespace std;
#define EXP 29
#define NUM_CHAR 2

struct Trie {
    int cnt;
    Trie* on;
    Trie* off;

    Trie(): cnt(0), on(0), off(0) {}
    ~Trie() {
        if (on) delete on;
        if (off) delete off;
    }
};

Trie* root = new Trie();

void insert(int num) {
    Trie *cur = root;
    for (int bit = 1 << EXP; bit > 0; bit >>= 1) {
        if (num & bit) {
            if (cur->on == 0) {
                cur->on = new Trie();
            }
            cur = cur->on;
        } else {
            if (cur->off == 0) {
                cur->off = new Trie();
            }
            cur = cur->off;
        }
        cur->cnt++;
    }
}

void erase(int num) {
    Trie *cur = root;
    for (int bit = 1 << EXP; bit > 0; bit >>= 1) {
        if (num & bit) {
            cur = cur->on;
        } else {
            cur = cur->off;
        }
        cur->cnt--;
    }
}

int maxXOR(int num) {
    int ret = 0;
    Trie *cur = root;
    for (int bit = 1 << EXP; bit > 0; bit >>= 1) {
        if (num & bit) {
            if (cur->off && cur->off->cnt) {
                ret += bit;
                cur = cur->off;
            } else {
                cur = cur->on;
            }
        } else {
            if (cur->on && cur->on->cnt) {
                ret += bit;
                cur = cur->on;
            } else {
                cur = cur->off;
            }
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    insert(0);

    int M; cin >> M;
    for (int i = 0; i < M; i++) {
        int q, x; cin >> q >> x;
        if (q == 1) {
            insert(x);
        } else if (q == 2) {
            erase(x);
        } else {
            cout << maxXOR(x) << '\n';
        }
    }
    return 0;
}