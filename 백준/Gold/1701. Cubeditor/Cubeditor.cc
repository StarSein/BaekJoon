#include <iostream>
#include <cstring>
#include <string>
using namespace std;

const int MAX_N = 5'000;
int table[MAX_N];
int answer = 0;

void makeTable(string p) {
    memset(table, 0, sizeof(table));
    int j = 0;
    for (int i = 1; i < p.size(); i++) {
        while (j > 0 && p[i] != p[j]) {
            j = table[j-1];
        }
        if (p[i] == p[j]) {
            table[i] = ++j;
            if (j > answer) {
                answer = j;
            }
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s; cin >> s;
    for (int lp = 0; lp < s.size() - 1; lp++) {
        makeTable(s.substr(lp));
    }
    cout << answer;
}