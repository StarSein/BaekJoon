#include <iostream>
#include <vector>
using namespace std;

const int SIZE = 360'000;
vector<bool> s(2 * SIZE), p(SIZE);
vector<int> table(SIZE);

void makeTable(vector<bool> &p) {
    int j = 0;
    for (int i = 1; i < p.size(); i++) {
        while (j > 0 && p[i] != p[j]) {
            j = table[j-1];
        }
        if (p[i] == p[j]) {
            table[i] = ++j;
        }
    }
}

void kmp(vector<bool> &s, vector<bool> &p) {
    int j = 0;
    for (int i = 0; i < s.size(); i++) {
        while (j > 0 && s[i] != p[j]) {
            j = table[j-1];
        }
        if (s[i] == p[j]) {
            if (j == p.size() - 1) {
                cout << "possible";
                return;
            } else {
                ++j;
            }
        }
    }
    cout << "impossible";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    int pos;
    for (int i = 0; i < n; i++) {
        cin >> pos;
        s[pos] = s[pos+SIZE] = true;
    }
    for (int i = 0; i < n; i++) {
        cin >> pos;
        p[pos] = true;
    }

    makeTable(p);
    kmp(s, p);
}