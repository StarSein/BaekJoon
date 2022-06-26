#include <iostream>
#include <cstring>
using namespace std;


const int MAX_N = 1e6;
int table[MAX_N];

void solve(string& p) {
    int j = 0;
    for (int i = 1; i < p.size(); i++) {
        while (j > 0 && p[i] != p[j]) {
            j = table[j-1];
        }
        if (p[i] == p[j]) {
            table[i] = ++j;
        }
    }
    if (table[p.size()-1] % (p.size() - table[p.size()-1]) == 0) {
        cout << p.size() / (p.size() - table[p.size()-1]) << '\n';
    } else {
        cout << 1 << '\n';
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    string s; cin >> s;
    while (s != ".") {
        memset(table, 0, s.size() * sizeof(int));
        solve(s);

        cin >> s;
    }
}