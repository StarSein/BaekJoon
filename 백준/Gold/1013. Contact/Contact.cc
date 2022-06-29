#include <iostream>
#include <regex>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    
    regex re("(100+1+|01)+");
    string inp;
    for (int i = 0; i < n; i++) {
        cin >> inp;

        cout << (regex_match(inp, re) ? "YES" : "NO") << '\n';
    }
}