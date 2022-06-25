#include <iostream>
#include <vector>
using namespace std;

vector<int> table;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int l; string s; cin >> l >> s;
    table.resize(l, 0);

    int j = 0;
    for (int i = 1; i < l; i++) {
        while (j > 0 && s[i] != s[j]) {
            j = table[j-1];
        }
        if (s[i] == s[j]) {
            table[i] = ++j;
        }
    }
    cout << l - table[l-1];
}