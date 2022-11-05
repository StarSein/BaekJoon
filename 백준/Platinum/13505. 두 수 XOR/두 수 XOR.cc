#include <iostream>
#include <vector>
using namespace std;
#define EXP 29

struct Trie {
    Trie* on;
    Trie* off;

    Trie(): on(0), off(0) {};
    ~Trie() {
        if (on) delete on;
        if (off) delete off;
    }
};

Trie* root = new Trie();
vector<int> nums;


void insert(int x) {
    Trie* cur = root;
    for (int bit = 1 << EXP; bit > 0; bit >>= 1) {
        if (x & bit) {
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
    }
}

int maxXOR(int x) {
    int ret = 0;
    Trie* cur = root;
    for (int bit = 1 << EXP; bit > 0; bit >>= 1) {
        if (x & bit) {
            if (cur->off != 0) {
                ret |= bit;
                cur = cur->off;
            } else {
                cur = cur->on;
            }
        } else {
            if (cur->on != 0) {
                ret |= bit;
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

    int N; cin >> N;
    nums.reserve(N);
    for (int i = 0; i < N; i++) {
        int num; cin >> num;
        nums.push_back(num);
        insert(num);
    }

    int ans = 0;
    for (int &num : nums) {
        ans = max(ans, maxXOR(num));
    }
    cout << ans;
    return 0;
}