#include <iostream>
#include <regex>
#include <string>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    string reStr; cin >> reStr;

    size_t ast_pos = reStr.find('*');
    string left = reStr.substr(0, ast_pos), right = reStr.substr(ast_pos+1, string::npos);

    regex re(left + ".*" + right);

    string inp;
    for (int i = 0; i < n; i++) {
        cin >> inp;

        cout << (regex_match(inp, re) ? "DA" : "NE") << '\n';
    }
}